package com.chariot.shadow.file;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.File;
import java.io.IOException;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@RequiredArgsConstructor
@Value
public class FileInfrastructure {

    private static final String FILE_NAME = "article.rss";
    @NonNull
    private String PREFIX_DIRECTORY;

    public void write(ArticleEntry news, File working) {
        File entryDirectory = null;
        try {
            entryDirectory = new File(working, PREFIX_DIRECTORY + news.getName());
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
