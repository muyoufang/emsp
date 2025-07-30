package com.demo.emsp.code.exception;


/**
 * 自定义异常
 */
public class SiteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public SiteException(int code) {
        this.code = code;
        this.msg = "未知错误";
    }

    public SiteException(int code, String... params) {
        this.code = code;
        this.msg = String.valueOf(params);
    }

    public SiteException(int code, Throwable e) {
        super(e);
        this.code = code;
        this.msg = e.getMessage();
    }

    public SiteException(String msg) {
        super(msg);
        this.code = 502;
        this.msg = msg;
    }

    public SiteException(String msg, Throwable e) {
        super(msg, e);
        this.code = 502;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
