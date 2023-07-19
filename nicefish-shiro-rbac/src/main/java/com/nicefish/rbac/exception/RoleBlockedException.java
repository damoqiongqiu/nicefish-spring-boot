package com.nicefish.rbac.exception;

import com.nicefish.core.exception.NiceFishBaseException;

/**
 * 角色锁定异常类
 *
 * @author 大漠穷秋
 */
public class RoleBlockedException extends NiceFishBaseException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super();
    }
}
