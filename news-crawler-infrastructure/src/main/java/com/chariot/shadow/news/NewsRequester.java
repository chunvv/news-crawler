package com.chariot.shadow.news;

import java.io.File;
import java.util.Date;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public interface NewsRequester {

    Date from();

    Date to();

    File workingDirectory();
}
