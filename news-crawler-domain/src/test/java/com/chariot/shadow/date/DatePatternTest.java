package com.chariot.shadow.date;

import mockit.Tested;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class DatePatternTest {

    @Tested
    private DatePattern datePattern;

    @Test
    public void generateYYYYMMDDPattern() throws Exception {
        assertThat(datePattern.generateFormatter(DatePattern.YYYY_MM_DD), Is.is(new SimpleDateFormat("yyyyMMdd")));
    }
}