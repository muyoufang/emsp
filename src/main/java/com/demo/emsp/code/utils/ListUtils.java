package com.demo.emsp.code.utils;

import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * @author muyoufang
 */
public class ListUtils {

    /**
     * 将数据转换为 string
     * 用于seo
     */
    public static String getStr2List(List<String> list) {
        StringBuffer strBuf = new StringBuffer("");

        for (String date : list) {
            strBuf.append(date);
            strBuf.append("\n");
        }
        String s = JSONUtil.toJsonStr(strBuf);
        return s;
    }

}
