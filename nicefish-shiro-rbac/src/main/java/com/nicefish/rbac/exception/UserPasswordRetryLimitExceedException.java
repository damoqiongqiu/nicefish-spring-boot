package com.nicefish.rbac.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 用户错误最大次数异常类
 *
 * @author 大漠穷秋
 */
public class UserPasswordRetryLimitExceedException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
