package com.nicefish.auth.exception;


import com.nicefish.core.exception.FishBaseException;

/**
 * 验证码错误异常类
 *
 * @author 大漠穷秋
 */
public class CaptchaException extends FishBaseException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error");
    }
}
