package com.nicefish.auth.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 用户账号已被删除
 *
 * @author 大漠穷秋
 */
public class UserDeleteException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete");
    }
}
