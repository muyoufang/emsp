package com.demo.emsp.code.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author muyoufang
 */
public class SystemUtils {

    public static String getIpAddress() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }

        return address.getHostAddress();
    }

    /**
     * @return Map<String, String>: key:磁盘盘符, value:磁盘剩余空间
     */
    public static String getHdInfo() {
        Map map = new TreeMap<String, String>();
        File[] roots = File.listRoots();
        double unit = Math.pow(1024, 3);

        String space = "";
        for (int i = 0; i < roots.length; i++) {
            String hd = roots[i].getPath();
            double freespace = roots[i].getFreeSpace() / unit;
            freespace = Math.ceil((freespace * 10)) / 10;
            map.put(hd, String.valueOf(freespace));
            if (i == 0) {
                space = String.valueOf(freespace);
            }
        }

        return space + "G";
    }

    public static String getJdk() {
        Map map = System.getenv();
        Iterator it = map.entrySet().iterator();
        String JAVA_HOME = null;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
//            System.out.print(entry.getKey() + "=");
//            System.out.println(entry.getValue());
            if (entry.getKey().equals("JAVA_HOME")) {
                JAVA_HOME = entry.getValue().toString();
            }
        }
        return JAVA_HOME;
    }

    /**
     * 获取browser name
     *
     * @return
     */
    public static String getBorderName() {
        String uaStr = ServletUtils.getRequest().getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getBrowser().toString();
    }

    /**
     * 获取System name
     *
     * @return
     */
    public static String getSystemName() {
        String uaStr = ServletUtils.getRequest().getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getOs().toString();
    }


}
