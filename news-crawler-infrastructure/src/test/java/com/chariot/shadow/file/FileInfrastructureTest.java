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
    private String directoryPrefix;

    @Test
    public void testWrite(@Mocked ArticleEntry articleEntry,
                          @Mocked SyndFeed feed,
                          @Mocked SyndFeedOutput output) throws Exception {
        File working = new File("/data/news");
        File directory = new File(working, "S1");
        File entry = new File(directory, "article.rss");
        
        new Expectations(fileInfrastructure) {{
            fileInfrastructure.determineNewsName(articleEntry); result = "S1";
            articleEntry.getFeed(); result = feed;
            new SyndFeedOutput().output(feed, entry);
        }};
        
        fileInfrastructure.write(articleEntry, working);
    }
}