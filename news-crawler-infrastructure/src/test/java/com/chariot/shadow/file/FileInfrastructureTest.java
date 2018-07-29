package com.chariot.shadow.file;

import com.chariot.shadow.news.common.ArticleEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedOutput;
import mockit.*;
import org.junit.Test;

import java.io.File;
import java.net.URL;

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
        URL link = new URL("http://google.com");
        
        new Expectations(fileInfrastructure) {{
            fileInfrastructure.determineNewsName(articleEntry); result = "S1";
            articleEntry.getLink(); result = link;
            fileInfrastructure.fetchData(directory, link);
        }};
        
        fileInfrastructure.write(articleEntry, working);
    }
}