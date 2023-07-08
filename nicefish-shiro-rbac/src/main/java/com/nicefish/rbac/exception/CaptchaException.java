package com.nicefish.rbac.exception;


import com.nicefish.core.exception.NiceFishBaseException;

/**
 * 验证码错误异常类
 *
 * @author 大漠穷秋
 */
public class CaptchaException extends NiceFishBaseException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error");
    }
}
