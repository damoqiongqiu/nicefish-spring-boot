package com.nicefish.auth.exception;

import com.nicefish.core.exception.FishBaseException;

/**
 * 角色锁定异常类
 *
 * @author 大漠穷秋
 */
public class RoleBlockedException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked");
    }
}
