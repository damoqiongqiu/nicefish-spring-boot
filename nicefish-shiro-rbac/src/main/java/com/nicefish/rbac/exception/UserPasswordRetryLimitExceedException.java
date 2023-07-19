package com.nicefish.rbac.exception;

import com.nicefish.core.exception.NiceFishBaseException;

/**
 * 用户错误最大次数异常类
 *
 * @author 大漠穷秋
 */
public class UserPasswordRetryLimitExceedException extends NiceFishBaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException() {
        super();
    }
}
