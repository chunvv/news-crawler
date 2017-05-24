package com.chariot.shadow.file;

import com.chariot.shadow.news.common.NewsRetrieverException;
import com.chariot.shadow.news.diamond.NewsEntity;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@Named
public class FileInfrastructure {

    private static final String FILE_NAME = "news.rss";

    public void write(NewsEntity news, File working) {
        File entryDirectory = null;
        try {
            entryDirectory = new File(working, news.generateUniqueFileName());
        } catch (NewsRetrieverException e) {
            throw new RuntimeException();
        }

        File entryFile = new File(entryDirectory, FILE_NAME);
        try {
            new SyndFeedOutput().output(news.getFeed(), entryFile);
        } catch (IOException | FeedException e) {
            throw new RuntimeException();
        }
    }
}
