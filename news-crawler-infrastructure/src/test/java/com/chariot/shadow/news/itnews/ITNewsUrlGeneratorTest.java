package com.chariot.shadow.news.itnews;

import mockit.Tested;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class ITNewsUrlGeneratorTest {

    @Tested
    private ITNewsUrlGenerator generator;

    @Test
    public void generateUrl() throws Exception {
        assertThat(generator.generate(), Is.is(new URL("https://www.itnews.com.au/RSS/rss.ashx")));
    }
}