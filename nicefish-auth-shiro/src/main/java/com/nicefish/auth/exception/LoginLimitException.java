package com.nicefish.auth.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 用户错误记数异常类
 *
 * @author 大漠穷秋
 */
public class LoginLimitException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public LoginLimitException(int retryLimitCount) {
        super("user.password.retry.limit.count", new Object[]{retryLimitCount});
    }
}
