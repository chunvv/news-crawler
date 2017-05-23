package com.chariot.shadow;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public interface UrlGenerator {

    URL generate() throws MalformedURLException;
}
