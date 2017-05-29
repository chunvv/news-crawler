package com.chariot.shadow.file;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import lombok.Value;

import java.io.File;
import java.io.IOException;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@Value
public class FileInfrastructure {

    private static final String FILE_NAME = "article.rss";
    private String directoryPrefix;

    public void write(ArticleEntry news, File working) {
        String newsName = determineNewsName(news);
        File entryDirectory = new File(working, newsName);
        entryDirectory.mkdir();
        File entryFile = new File(entryDirectory, FILE_NAME);
        
        try {
            new SyndFeedOutput().output(news.getFeed(), entryFile);
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }

    String determineNewsName(ArticleEntry news) {
        try {
            return directoryPrefix + news.getName();    
        } catch(NewsRetrieverException e) {
            return directoryPrefix;
        }
    }
}
