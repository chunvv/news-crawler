package com.chariot.shadow.supplier.factory;

import com.chariot.shadow.supplier.*;
import mockit.Mocked;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class SupplierFactoryTest {

    @Test
    public void createSupplierBySupplierType() throws Exception {
        Supplier supplier = SupplierFactory.create(SupplierType.SKY_NEWS);
        assertThat(supplier.getIdAsInt(), is(1));
        assertThat(supplier.getCodeAsString(), is("S"));
        assertThat(supplier.getNameAsString(), is("Sky News"));
    }

    @Test
    public void createSupplierByEachField(@Mocked SupplierID id, @Mocked SupplierCode code, @Mocked SupplierName name) throws Exception {
        Supplier supplier = SupplierFactory.create(id, code, name);
        assertThat(supplier.getId(), is(id));
        assertThat(supplier.getCode(), is(code));
        assertThat(supplier.getName(), is(name));
    }

    @Test
    public void createSupplierByEachValue() throws Exception {
        Supplier supplier = SupplierFactory.create(1, "S", "Sky News");
        assertThat(supplier.getIdAsInt(), is(1));
        assertThat(supplier.getCodeAsString(), is("S"));
        assertThat(supplier.getNameAsString(), is("Sky News"));
    }
}