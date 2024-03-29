package com.xmz.application.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmz.application.common.UserContext;
import com.xmz.application.model.common.AuthorizeUser;
import com.xmz.application.model.common.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2021/9/13
 * 权限过滤器
 */
@Slf4j
@Component
public class AuthFilter implements Filter {


    @Resource
    private ObjectMapper objectMapper;

    @Value("${auth.authExcludePaths:''}")
    private String authExcludePaths;

    @Value("${auth.useRedis:false}")
    private Boolean useRedis;

    @Resource
    private RedisTemplate redisTemplate;


    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static List<String> pathList = null;

    static {
        pathList = new LinkedList<>();
        pathList.add("/sysUser/login");
        pathList.add("/sysUser/register");
        pathList.add("/sysUser/getVerifyCode");
        pathList = Collections.unmodifiableList(pathList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        log.info("sessionId={}", request.getSession().getId());
        String accessUrl = request.getRequestURI();
        if (isExcludePath(accessUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            AuthorizeUser authorizeUser = null;
            if(useRedis){
                authorizeUser = (AuthorizeUser) redisTemplate.opsForValue().get(AuthorizeUser.USER_KEY + ":" + request.getSession().getId());
            } else {
                authorizeUser = (AuthorizeUser) request.getSession().getAttribute(AuthorizeUser.USER_KEY);
            }
            if(authorizeUser == null) {
                responseMsg(response, HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.value(), "用户没登录");
                return;
            }
            UserContext.setAuthorizeUser(authorizeUser);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("发生错误", e);
        } finally {
            UserContext.remove();
        }

    }


    public boolean isExcludePath(String url) {
        log.debug("请求url:{}", url);
        List<String> excludePathList = new LinkedList<>(pathList);

        final String NULL_STR = "null";
        if(!NULL_STR.equals(authExcludePaths) && StringUtils.hasLength(authExcludePaths)){
            excludePathList.addAll(Arrays.asList(authExcludePaths.split(",")));
        }

        for (String pattern : excludePathList) {
            if (antPathMatcher.match(pattern, url)) {
                return true;
            }
        }
        return false;
    }

    private void responseMsg(HttpServletResponse response,Integer httpStatus, Integer status, String message) throws IOException {
        response.setStatus(httpStatus);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                RespResult.build().setStatus(status).setMsg(message)));
    }

}
