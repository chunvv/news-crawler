package com.chariot.shadow.file;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public abstract class FileInfrastructure {

    protected static final String FILE_NAME = "article.html";
    protected String directoryPrefix;

    public void write(ArticleEntry news, File working) {
        if (news.getEntry() == null)
            return;

        String newsName = determineNewsName(news);
        File entryDirectory = new File(working, newsName);
        entryDirectory.mkdir();

        try {
            URL link = news.getLink();
            fetchData(entryDirectory, link);
        } catch (IOException | NewsRetrieverException e) {
            e.printStackTrace();
        }
    }

    protected abstract void fetchData(File entryDir, URL link) throws NewsRetrieverException, IOException;

    String determineNewsName(ArticleEntry news) {
        try {
            return directoryPrefix + news.getName();
        } catch (NewsRetrieverException e) {
            return directoryPrefix;
        }
    }
}
