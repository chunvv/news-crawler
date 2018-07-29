package com.chariot.shadow.supplier;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class SupplierTypeTest {

    @Test
    public void getSupplierTypeById() throws Exception {
        assertThat(SupplierType.get(1), is(SupplierType.SKY_NEWS));
        assertThat(SupplierType.get(2), is(SupplierType.IT_NEWS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenGetSupplierByInvalidId() throws Exception {
        SupplierType.get(1000);
    }
}