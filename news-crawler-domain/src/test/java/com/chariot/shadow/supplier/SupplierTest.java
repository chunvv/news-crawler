package com.chariot.shadow.supplier;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class SupplierTest {

    private Supplier supplier;

    public SupplierTest() {
        this.supplier = createTestSupplier();
    }

    @Test
    public void getIdAsInt() throws Exception {
        assertThat(supplier.getIdAsInt(), is(1));
    }

    @Test
    public void getCodeAsString() throws Exception {
        assertThat(supplier.getCodeAsString(), is("code"));
    }

    @Test
    public void getNameAsString() throws Exception {
        assertThat(supplier.getNameAsString(), is("name"));
    }

    private Supplier createTestSupplier() {
        return new Supplier(
                new SupplierID(1),
                new SupplierCode("code"),
                new SupplierName("name")
        );
    }
}