package com.nicefish.rbac.exception;

import com.nicefish.core.exception.NiceFishBaseException;

/**
 * 用户账号已被删除
 *
 * @author 大漠穷秋
 */
public class UserDeleteException extends NiceFishBaseException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super();
    }
}
