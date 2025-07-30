package com.demo.emsp.business.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验室检测状态
 */
public enum StationStatusEnum {
    OPERATION_ING("正常运营", 0),
    OPERATION_ED("停止运营", 1);

    private final int value;
    private final String lable;

    StationStatusEnum(String lable, int value) {
        this.lable = lable;
        this.value = value;
    }

    public static final List<Map<String, Object>> infoList = new ArrayList<>();

    public static StationStatusEnum getByValue(int value) {
        for (StationStatusEnum v : StationStatusEnum.values()) {
            if (v.value == value) {
                return v;
            }
        }
        return null;
    }

    public static String getLabelByValue(int value) {
        StationStatusEnum obj = getByValue(value);
        if (obj != null) {
            return obj.lable;
        }
        return "";
    }

    static {
        for (StationStatusEnum v : StationStatusEnum.values()) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", v.value);
            info.put("lable", v.lable);
            infoList.add(info);
        }
    }
}
