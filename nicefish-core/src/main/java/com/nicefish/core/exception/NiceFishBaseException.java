package com.nicefish.core.exception;

/**
 *
 * @author 大漠穷秋
 */
public class NiceFishBaseException extends RuntimeException {
    private String module;
    private String code;
    private Object[] args;
    private String defaultMessage;

    public NiceFishBaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public NiceFishBaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public NiceFishBaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public NiceFishBaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public NiceFishBaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
