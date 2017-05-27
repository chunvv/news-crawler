package com.chariot.shadow.news.itnews;

import com.chariot.shadow.news.UrlGenerator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class for generating rss feed url for IT News supplier
 * <p>
 * Created by Trung Vu on 2017/05/27.
 */
public class ITNewsUrlGenerator implements UrlGenerator {
    @Override
    public URL generate() throws MalformedURLException {
        return new URL("https://www.itnews.com.au/RSS/rss.ashx");
    }
}
