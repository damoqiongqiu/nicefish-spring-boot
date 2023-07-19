package com.nicefish.rbac.exception;

import com.nicefish.core.exception.NiceFishBaseException;

/**
 * 用户错误记数异常类
 *
 * @author 大漠穷秋
 */
public class LoginLimitException extends NiceFishBaseException {
    private static final long serialVersionUID = 1L;

    public LoginLimitException() {
        super();
    }
}
