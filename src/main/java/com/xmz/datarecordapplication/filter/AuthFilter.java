package com.xmz.datarecordapplication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmz.datarecordapplication.common.UserContext;
import com.xmz.datarecordapplication.model.AuthorizeUser;
import com.xmz.datarecordapplication.model.common.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("authExcludePaths:''")
    private String authExcludePaths;


    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static List<String> pathList = null;

    static {
        pathList = new LinkedList<>();
        //pathList.add("/**");
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
        String accessUrl = request.getRequestURI();
        if (isExcludePath(accessUrl)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            AuthorizeUser authorizeUser = (AuthorizeUser)request.getSession().getAttribute(AuthorizeUser.USER_KEY);
            if(authorizeUser == null) {
                responseMsg(response, -1, "用户没登录");
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

    private void responseMsg(HttpServletResponse response, Integer status, String message) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                RespResult.build().setStatus(status).setMsg(message)));
    }

}
