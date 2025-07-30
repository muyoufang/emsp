package com.demo.emsp.code.utils;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author muyoufang
 */
@Component
public class ThymeleafUtil {

    @Resource
    private TemplateEngine templateEngine;

    @Value("${project.url}")
    private String projectUrl;

    @Value("${project.path}")
    private String filePath;

    /**
     * 生成静态页面
     *
     * @param templateName 放在根路径templates下的的模板文件的名称
     * @param dest         带路径的目标文件
     * @param data         数据
     * @param key          模板中的key
     * @return 成功返回true，失败返回false
     */
    public String genPage(String templateName, String dest, Object data, String key) {
        // 创建上下文，
        Context context = new Context();
        // 把数据加入上下文
        Map<String, Object> map = Maps.newConcurrentMap();
        map.put(key, data);
        context.setVariables(map);

        File file = new File(filePath + "/web");
        if (!file.exists()){
            file.mkdir();
        }
        // 创建输出流，关联到一个临时文件
        File destFile = new File(file, dest);
        // 备份原页面文件
        try (PrintWriter writer = new PrintWriter(destFile, "UTF-8")) {
            // 利用thymeleaf模板引擎生成 静态页面
            templateEngine.process(templateName, context, writer);

            return projectUrl + "/file/web/" + dest;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
