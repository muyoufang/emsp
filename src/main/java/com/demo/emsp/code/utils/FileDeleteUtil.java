package com.demo.emsp.code.utils;

import java.io.File;

/**
 * 这个类是专门用于删除文件夹的
 *
 * @author muyoufang
 */
public class FileDeleteUtil {
    /**
     * 进行指定目录的删除 或文件的删除
     *
     * @param index 目录对象
     */
    public static void delFile(File index) {
        if (index.isDirectory()) {
            File[] files = index.listFiles();
            for (File in : files) {
                delFile(in);
            }
        } else {
            if (index.exists()) {
                index.delete();
            }
        }
        index.delete();
    }

    /**
     * 进行多目录的删除
     */
    public static void delFile(String... fileNames) {
        for (int i = 0; i < fileNames.length; i++) {
            delFile(new File(fileNames[i]));
        }
    }

    /**
     * 进行单目录的删除
     */
    public static void delFile(String fileName) {
        delFile(new File(fileName));
    }
}
