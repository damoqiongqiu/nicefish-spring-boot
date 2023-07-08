package com.nicefish.rbac.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 用户不存在异常类
 *
 * @author 大漠穷秋
 */
public class UserNotExistsException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists");
    }
}
