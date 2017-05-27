package com.chariot.shadow;

import com.chariot.shadow.news.skynews.SkyNewsUrlGenerator;
import mockit.Tested;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class SkyNewsUrlGeneratorTest {

    @Tested
    private SkyNewsUrlGenerator generator;

    @Test
    public void generateNewsUrl() throws Exception {
        assertThat(generator.generate(), Is.is(new URL("http://feeds.skynews.com/feeds/rss/technology.xml")));
    }
}