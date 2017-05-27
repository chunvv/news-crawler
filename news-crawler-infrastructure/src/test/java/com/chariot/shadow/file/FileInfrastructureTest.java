package com.chariot.shadow.file;

import com.chariot.shadow.news.common.ArticleEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedOutput;
import mockit.*;
import org.junit.Test;

import java.io.File;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class FileInfrastructureTest {

    @Tested
    private FileInfrastructure fileInfrastructure;
    @Injectable
    private String PREFIX_DIRECTORY = "D";

    @Test
    public void writeToDisk(@Mocked ArticleEntry news, 
                            @Mocked SyndFeedOutput output,
                            @Mocked SyndFeed syndFeed) throws Exception {
        File working = new File("/data/news/skynews");
        File entryDirectory = new File(working, "D1");
        File entryFile = new File(entryDirectory, "article.rss");
        
        new Expectations() {{
            news.getName(); result = "1";
            new File(working, "1"); result = entryDirectory;
            new File(entryDirectory, "article.rss"); result = entryFile;
            entryDirectory.mkdir(); result = any;
            new SyndFeedOutput(); result = output;
            news.getFeed(); result = syndFeed;
        }};

        fileInfrastructure.write(news, working);
    }
}