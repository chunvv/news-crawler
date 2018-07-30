package com.chariot.shadow.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@AllArgsConstructor
@Getter
public enum SupplierType {

    SKY_NEWS(1, "S", "Sky News"),
    IT_NEWS(2, "I", "IT News"),
    VN_EXPRESS(3, "V", "VN Express"),
    VN_EXPRESS_BUSINESS(4, "V4-", "VN Express Business"),
    VN_EXPRESS_SCIENCE(5, "V5-", "VN Express Science"),
    VTV_TECHNOLOGY(10, "V10-", "VTV News Technology");

    private int id;
    private String code;
    private String name;

    public static SupplierType get(int id) {
        return Stream.of(values()).filter(value -> value.id == id).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
