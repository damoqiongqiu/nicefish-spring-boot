package com.nicefish.auth.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 *
 * @author 大漠穷秋
 */
public class UserPasswordNotMatchException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match");
    }
}
