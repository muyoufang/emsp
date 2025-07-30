package com.demo.emsp.code.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author muyoufang
 */
public class TimeUtils {

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getCreateTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyyMMdd
     */
    public static String getTodayTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 过期时间设置
     *
     * @return
     */
    public static int getTokenTimeOut() {
//        return 7 * 24 * 60 * 60;
        /*
         *   1小时60*60
         */
        return 60 * 60;
    }
}
