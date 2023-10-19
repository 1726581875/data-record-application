package com.xmz.application.common;


import com.xmz.application.common.exception.SysUnauthorizedException;
import com.xmz.application.model.common.AuthorizeUser;


/**
 * @author xmz
 * @date 2022/09/06
 */
public final class UserContext {

    /**
     * InheritableThreadLocal 里存储的值会在新建子线程的时候被继承
     */
    private static ThreadLocal<AuthorizeUser> authorizeUserThreadLocal = new InheritableThreadLocal<>();


    public static void setAuthorizeUser(AuthorizeUser authorizeUser) {
        authorizeUserThreadLocal.set(authorizeUser);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public static AuthorizeUser getAuthorizeUser() {
        AuthorizeUser loginUser = authorizeUserThreadLocal.get();
        if (loginUser == null) {
            throw new SysUnauthorizedException("获取用户失败");
        }
        return loginUser;
    }

    /**
     * 使用完记得remove,防止内存泄露
     */
    public static void remove() {
        authorizeUserThreadLocal.remove();
    }

}
