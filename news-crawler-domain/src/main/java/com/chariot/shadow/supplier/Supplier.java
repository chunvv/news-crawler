package com.chariot.shadow.supplier;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Supplier contains some information as id, supplier code and supplier name
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
@Value
@AllArgsConstructor
public class Supplier {

    private SupplierID id;
    private SupplierCode code;
    private SupplierName name;
}
