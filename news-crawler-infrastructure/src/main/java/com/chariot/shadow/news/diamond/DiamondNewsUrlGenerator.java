package com.chariot.shadow.news.diamond;

import com.chariot.shadow.UrlGenerator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class DiamondNewsUrlGenerator implements UrlGenerator {

    @Override
    public URL generate() throws MalformedURLException {
        return new URL("http://diamond.jp/list/feed/rss4newspicks");
    }
}
