package com.xmz.datarecordapplication.service.sys.impl;

import com.xmz.datarecordapplication.common.exception.ValidationException;
import com.xmz.datarecordapplication.common.util.VerificationCode;
import com.xmz.datarecordapplication.mapper.sys.SysUserMapper;
import com.xmz.datarecordapplication.model.AuthorizeUser;
import com.xmz.datarecordapplication.model.VerifyCode;
import com.xmz.datarecordapplication.model.entity.sys.SysUser;
import com.xmz.datarecordapplication.model.param.sys.LoginParam;
import com.xmz.datarecordapplication.model.param.sys.RegisterParam;
import com.xmz.datarecordapplication.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaomingzhang
 * @date 2022/9/5
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Value("${auth.useRedis:false}")
    private Boolean useRedis;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String VERIFY_CODE = "VERIFY_CODE";


    @Override
    public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String codeText = verificationCode.getText();

        log.info("获取验证码，sessionId={},code={}",request.getSession().getId(), codeText);
        VerifyCode verifyCode = new VerifyCode(codeText, System.currentTimeMillis() + 1000 * 60 * 5);
        // 验证码放到session,有效时间为5分钟
        if(useRedis) {
            redisTemplate.opsForValue().set(VERIFY_CODE + ":"  +request.getSession().getId(), verifyCode, 5, TimeUnit.MINUTES);
        } else {
            request.getSession().setAttribute(VERIFY_CODE,verifyCode);
        }
        VerificationCode.output(image,response.getOutputStream());
    }

    @Override
    public void login(LoginParam param) {

        HttpServletRequest request = getHttpServletRequest();
        HttpSession session = request.getSession();

        VerifyCode verifyCode = null;
        // 校验验证码
        if(useRedis){
            verifyCode = (VerifyCode)redisTemplate.opsForValue().get(VERIFY_CODE + ":"  +request.getSession().getId());
        } else {
            verifyCode = (VerifyCode)session.getAttribute(VERIFY_CODE);
        }
        if(verifyCode == null || !verifyCode.getCode().toLowerCase()
                .equals(param.getVerifyCode().toLowerCase())) {
            log.warn("验证码不正确,,sessionId={},输入的code={}, session存储的code={}", session.getId(), param.getVerifyCode(), verifyCode);
            throw new ValidationException("验证码不正确");
        }
        if(VerifyCode.isExpired(verifyCode.getExpirationTime())) {
            log.warn("验证码不正确,verifyCode={}", verifyCode);
            throw new ValidationException("验证码已过期");
        }

        // 校验账号密码
        SysUser user = sysUserMapper.getUserByAccount(param.getAccount());
        if(user == null) {
            throw new ValidationException("账号" + param.getAccount() + "不存在");
        }
        // todo 密码暂时不做加密
        if(!param.getPassword().equals(user.getPassword())) {
            log.warn("user={},param={}", user, param);
            throw new ValidationException("密码错误");
        }


        AuthorizeUser sysUser = new AuthorizeUser(user.getId(), user.getName(), user.getAccount());
        sysUser.setTenantId(user.getTenantId());
        // 用户存储到session
        if(useRedis){
            redisTemplate.opsForValue().set(AuthorizeUser.USER_KEY + ":"  +request.getSession().getId(), sysUser);
        } else {
            request.getSession().setAttribute(AuthorizeUser.USER_KEY, sysUser);
        }

    }



    /**
     * 获取spring上下文中存储的HttpServletRequest
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            throw new RuntimeException("无法获取到HttpServletRequest,因为attributes为空");
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) attributes;
        return requestAttributes.getRequest();
    }

    @Override
    public void loginOut() {
        HttpServletRequest request = getHttpServletRequest();
        if (useRedis) {
            redisTemplate.delete(AuthorizeUser.USER_KEY + ":" + request.getSession().getId());
        } else {
            request.getSession().removeAttribute(AuthorizeUser.USER_KEY);
        }
    }

    @Override
    public void register(RegisterParam param) {
        // 校验账号密码
        SysUser user = sysUserMapper.getUserByAccount(param.getAccount());
        if(user != null) {
            throw new ValidationException("账号" + param.getAccount() + "已存在");
        }
        // todo 密码暂时不做加密
        if(!param.getPassword().equals(param.getConfirmPassword())) {
            log.warn("密码不一致，param={}", param);
            throw new ValidationException("密码不一致");
        }

        SysUser sysUser = new SysUser();
        sysUser.setAccount(param.getAccount());
        sysUser.setPassword(param.getPassword());
        sysUser.setName(param.getAccount());

        sysUserMapper.insert(sysUser);
    }
}
