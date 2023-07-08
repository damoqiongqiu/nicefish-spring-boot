package com.nicefish.rbac.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 用户锁定异常类
 *
 * @author 大漠穷秋
 */
public class UserBlockedException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super("user.blocked");
    }
}
