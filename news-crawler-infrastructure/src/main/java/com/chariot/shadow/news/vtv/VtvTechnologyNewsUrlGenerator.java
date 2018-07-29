package com.chariot.shadow.news.vtv;

import com.chariot.shadow.news.UrlGenerator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class for generating rss feed url for SkyNews supplier
 * 
 * Created by Trung Vu on 2017/05/23.
 */
public class VtvTechnologyNewsUrlGenerator implements UrlGenerator {

    @Override
    public URL generate() throws MalformedURLException {
        return new URL("https://vtv.vn/cong-nghe.rss");
    }
}
