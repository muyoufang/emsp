/**
 * Copyright (c) 2016-2019  All rights reserved.
 * <p>
 * https://www.7-me.net
 * <p>
 * 版权所有，侵权必究！
 */

package com.demo.emsp.code.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Map工具类
 */
public class MapUtil extends HashMap<String, Object> {

    private static final Logger log = LoggerFactory.getLogger(MapUtil.class);

    @Override
    public MapUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /************************************************************************
     * @author: wg
     * @description: 判断 map 的值 是否都为空
     * @params:
     * @return:
     * @createTime: 14:46  2022/4/14
     * @updateTime: 14:46  2022/4/14
     ************************************************************************/
    public static boolean isAllEmptyValue(Map<String, Object> params) {
        if (params == null || params.size() == 0) return true;
        boolean b = false;
        for (Entry<String, Object> entry : params.entrySet()) {
            if (ObjectUtils.isEmpty(entry.getValue())) {
                b = true;
            } else {
                return false;
            }
        }
        return b;
    }

    public static <T> boolean allEmptyValue(Map<Long, T> params) {
        if (params == null || params.size() == 0) return true;

        boolean b = false;
        for (Entry<Long, T> entry : params.entrySet()) {
            if (ObjectUtils.isEmpty(entry.getValue())) {
                b = true;
            } else {
                return false;
            }
        }
        return b;
    }

    public static <T> boolean isEmpty(Map<Long, T> map) {
        return null == map || map.size() == 0;
    }

    public static <K, V> Map<K, V> removeKeys(Map<K, V> map, final K... keys) {
        for (K key : keys) {
            map.remove(key);
        }
        return map;
    }

    /************************************************************************
     * @author: wg
     * @description: 是否还有其他 key, keySet 里的不算,
     * @params:
     * @return:
     * @createTime: 16:58  2022/9/15
     * @updateTime: 16:58  2022/9/15
     ************************************************************************/
    public static boolean hasOtherKey(Map<String, Object> map, Set<String> keySet) {
        Map<String, Object> map2 = new HashMap<>(map);

        for (String key : keySet) {
            map2.remove(key);
        }

        return !map2.isEmpty();
    }

    public static boolean containsKey(Map<String, Object> map, Set<String> keySet) {
        for (String s : keySet) {
            if (map.containsKey(s)) return true;
        }

        return false;
    }

    /************************************************************************
     * @author: wg
     * @description: 值为空
     * @params:
     * @return:
     * @createTime: 13:44  2022/10/10
     * @updateTime: 13:44  2022/10/10
     ************************************************************************/
    public static boolean isEmpty(Map<String, Object> map, String key) {
        if (map.get(key) == null) return true;
        if (!map.containsKey(key)) return true;
        Object obj = map.get(key);
        if (ObjectUtils.isEmpty(obj.toString())) return true;
        if (!ObjectUtils.isEmpty(obj.toString())) {
            return StringUtils.isBlank(obj.toString()) || "null".equals(obj.toString()) || "Null".equals(obj.toString()) || "NULL".equals(obj.toString());
        }
        return false;
    }

    /************************************************************************
     * @author: wg
     * @description: 层级结构的map 根据key获取 值
     * @params: key = wg.jwt.secret
     * @return:
     * @createTime: 10:57  2023/5/26
     * @updateTime: 10:57  2023/5/26
     ************************************************************************/
    public static Object get(Map<String, Object> hierarchyMap, String key) {
        if (hierarchyMap == null || key == null) {
            return null;
        }

        String[] split = key.split("\\.");
        if (!hierarchyMap.containsKey(split[0])) {
            return null;
        }

        Object obj = hierarchyMap.get(split[0]);
        if (obj instanceof Map) {
            return get((Map<String, Object>) obj, key.substring(key.indexOf(".") + 1));
        } else {
            return obj;
        }
    }

    /**
     * @author: wg
     * @description: 实体类 转 map, 忽略 null 值
     * @params:
     * @return:
     * @createTime: 13:36  2023/9/5
     * @updateTime: 13:36  2023/9/5
     */
    public static <T> Map<String, Object> bean2MapIgnoreNullValue(T obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            Field[] fields = obj.getClass().getDeclaredFields(); // 获取实体类的全部成员变量
            for (Field field : fields) {
                field.setAccessible(true); // 设置字段可访问以便取值
                if (field.get(obj) != null) {
                    map.put(field.getName(), field.get(obj)); // 将实体类对象的成员变量的键值对放入 Map 中
                }
            }
        } catch (IllegalAccessException e) {
            log.error("error:{}", e);
            return null;
        }

        return map;
    }

    /************************************************************************
     * @author: wg
     * @description: 实体类 转 map
     * @params:
     * @return:
     * @createTime: 15:11  2023/6/1
     * @updateTime: 15:11  2023/6/1
     ************************************************************************/
    public static <T> Map<String, Object> bean2Map(T obj) {
        if (obj == null) {
            return new HashMap<>();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Field[] fields = obj.getClass().getDeclaredFields(); // 获取实体类的全部成员变量
            for (Field field : fields) {
                field.setAccessible(true); // 设置字段可访问以便取值
                map.put(field.getName(), field.get(obj)); // 将实体类对象的成员变量的键值对放入 Map 中
            }
        } catch (IllegalAccessException e) {
            log.error("error:{}", e);
            return null;
        }
        return map;
    }

    /************************************************************************
     * @author: wg
     * @description: remove 掉 entity 字段
     * @params:
     * @return:
     * @createTime: 11:20  2023/9/6
     * @updateTime: 11:20  2023/9/6
     ************************************************************************/
    public static <D, E> Map<String, Object> removeEntityFields(D dto, E entity) {
        Map<String, Object> dtoMap = bean2Map(dto);

        if (dtoMap != null) {
            Field[] entityFields = entity.getClass().getDeclaredFields();

            for (Field field : entityFields) {
                dtoMap.remove(field.getName());
            }
        }

        return dtoMap;
    }
}
