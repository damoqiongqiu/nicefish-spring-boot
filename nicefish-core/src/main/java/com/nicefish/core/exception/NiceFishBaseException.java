package com.nicefish.core.exception;

/**
 * NiceFish 的基础异常类，所有业务异常都应该继承此类。
 * NiceFish 的日志分析工具可以针对此异常类的格式进行日志分析和审计。
 * @author 大漠穷秋
 */
public class NiceFishBaseException extends RuntimeException {
    //异常所属的业务模块
    private String module;

    //异常代码
    private String code;

    //异常消息
    private String message;

    public NiceFishBaseException() {
        super();
    }

    public NiceFishBaseException(Throwable cause) {
        super(cause);
    }

    public NiceFishBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NiceFishBaseException(String module, String code, String message) {
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public NiceFishBaseException(String message) {
        this(null, null, message);
    }

    public NiceFishBaseException(String module, String code) {
        this(module, code, null);
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
