package com.chariot.shadow.file;

import com.chariot.shadow.news.common.NewsRetrieverException;
import com.chariot.shadow.news.diamond.Article;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import java.io.File;
import java.io.IOException;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public class FileInfrastructure {

    private static final String FILE_NAME = "news.rss";

    public void write(Article news, File working) {
        File entryDirectory = null;
        try {
            entryDirectory = new File(working, news.generateUniqueFileName());
        } catch (NewsRetrieverException e) {
            e.printStackTrace();
        }

        File entryFile = new File(entryDirectory, FILE_NAME);
        entryDirectory.mkdir();
        try {
            new SyndFeedOutput().output(news.getFeed(), entryFile);
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }
}
