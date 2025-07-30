package com.demo.emsp.business.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验室检测状态
 */
public enum StationTypeEnum {
    COMMON("公共", 1),
    PERSONAL("个人", 2),
    SPECIAL("专用", 3),
    OTHER("其它", 4);

    private final int value;
    private final String lable;

    StationTypeEnum(String lable, int value) {
        this.lable = lable;
        this.value = value;
    }

    public static final List<Map<String, Object>> infoList = new ArrayList<>();

    public static StationTypeEnum getByValue(int value) {
        for (StationTypeEnum v : StationTypeEnum.values()) {
            if (v.value == value) {
                return v;
            }
        }
        return null;
    }

    public static String getLabelByValue(int value) {
        StationTypeEnum obj = getByValue(value);
        if (obj != null) {
            return obj.lable;
        }
        return "";
    }

    static {
        for (StationTypeEnum v : StationTypeEnum.values()) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", v.value);
            info.put("lable", v.lable);
            infoList.add(info);
        }
    }
}
