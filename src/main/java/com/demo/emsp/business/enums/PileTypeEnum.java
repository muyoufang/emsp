package com.demo.emsp.business.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验室检测状态
 */
public enum PileTypeEnum {
    //直流、交流、交直一体、其它
    DC("直流"),
    AC("交流"),
    ADC("交直一体"),
    OTHER("其它");

    public static final List<String> infoList = new ArrayList<>();

    static {
        for (PileTypeEnum v : PileTypeEnum.values()) {
            infoList.add(v.value);
        }
    }

    private final String value;

    PileTypeEnum(String value) {
        this.value = value;
    }

    public static PileTypeEnum getByValue(String value) {
        for (PileTypeEnum v : PileTypeEnum.values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        return null;
    }
}
