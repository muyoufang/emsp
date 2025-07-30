package com.demo.emsp.business.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验室检测状态
 */
public enum StationModelEnum {
    SELF_SUPPORT("自营", 1),
    JOINT_VENTURE ("联营", 2),
    SPECIAL_USE("专用", 3);

    private final int value;
    private final String lable;

    StationModelEnum(String lable, int value) {
        this.lable = lable;
        this.value = value;
    }

    public static final List<Map<String, Object>> infoList = new ArrayList<>();

    public static StationModelEnum getByValue(int value) {
        for (StationModelEnum v : StationModelEnum.values()) {
            if (v.value == value) {
                return v;
            }
        }
        return null;
    }

    public static String getLabelByValue(int value) {
        StationModelEnum obj = getByValue(value);
        if (obj != null) {
            return obj.lable;
        }
        return "";
    }

    static {
        for (StationModelEnum v : StationModelEnum.values()) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", v.value);
            info.put("lable", v.lable);
            infoList.add(info);
        }
    }
}
