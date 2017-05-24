package com.chariot.shadow.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@AllArgsConstructor
@Getter
public enum SupplierType {

    DIAMOND(1, "D", "Diamond Online");

    private int id;
    private String code;
    private String name;
}
