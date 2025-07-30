package com.demo.emsp.code.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 回调类 返回实体类
 */
@Data
public class R implements Serializable {

    public int code;
    public String msg;

    public Object data;

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static R ok() {
        return ok(200, "操作成功");
    }

    public static R ok(Object t) {
        R r = ok(200, "操作成功");
        r.setData(t);
        return r;
    }

    public static R ok(int code, String msg) {
        return new R(code, msg);
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error() {
        return error(500, "操作失败！");
    }

    public static R error(int code, String msg) {
        return new R(code, msg);
    }
}
