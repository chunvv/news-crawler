package com.chariot.shadow.news.skynews;

import com.chariot.shadow.news.UrlGenerator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class for generating rss feed url for SkyNews supplier
 * 
 * Created by Trung Vu on 2017/05/23.
 */
public class SkyNewsUrlGenerator implements UrlGenerator {

    @Override
    public URL generate() throws MalformedURLException {
        return new URL("http://feeds.skynews.com/feeds/rss/technology.xml");
    }
}
