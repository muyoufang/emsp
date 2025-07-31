package com.demo.emsp.business.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实验室检测状态
 */
public enum PileStatusEnum {
    AVAILABLE("可用的", "AVAILABLE"),
    BLOCKED("使用中", "BLOCKED"),
    INOPERATIVE("维护中", "INOPERATIVE"),
    REMOVED("已停用", "REMOVED");

    public static final List<Map<String, Object>> infoList = new ArrayList<>();

    static {
        for (PileStatusEnum v : PileStatusEnum.values()) {
            Map<String, Object> info = new HashMap<>();
            info.put("value", v.value);
            info.put("lable", v.lable);
            infoList.add(info);
        }
    }

    private final String value;
    private final String lable;

    PileStatusEnum(String lable, String value) {
        this.lable = lable;
        this.value = value;
    }

    public static PileStatusEnum getByValue(String value) {
        for (PileStatusEnum v : PileStatusEnum.values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        return null;
    }

    public static String checkConversion(String oStatus, String nStatus) {
        if (BLOCKED.value.equals(oStatus) && INOPERATIVE.value.equals(nStatus)) {
            return "BLOCKED状态不能转为INOPERATIVE状态";
        }
        if (BLOCKED.value.equals(nStatus) && INOPERATIVE.value.equals(oStatus)) {
            return "INOPERATIVE状态不能修改为BLOCKED状态";
        }
        if (REMOVED.value.equals(oStatus)) {
            return "REMOVED状态不能修改";
        }
        return "";
    }

    public static String getLabelByValue(String value) {
        PileStatusEnum obj = getByValue(value);
        if (obj != null) {
            return obj.lable;
        }
        return "";
    }

    public String getValue() {
        return value;
    }

    public String getLable() {
        return lable;
    }
}
