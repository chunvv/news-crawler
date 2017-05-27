package com.chariot.shadow.news.common;

import com.chariot.shadow.news.UrlGenerator;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/28.
 */
public class FeedNewsSourceRetrieverTest {
    
    @Tested
    private FeedNewsSourceRetriever feedNewsSourceRetriever;
    @Injectable
    private UrlGenerator generator;
    @Injectable
    private NewsRequester newsRequester;
    
    @Test
    public void excludeCandidatesByDateRange(@Mocked SyndEntry entry1, @Mocked SyndEntry entry2) throws Exception {
        List<SyndEntry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);
        
        new Expectations() {{
            newsRequester.from(); result = new SimpleDateFormat("yyyyMMdd").parse("20170521");
            newsRequester.to(); result = new SimpleDateFormat("yyyyMMdd").parse("20170523");
            entry1.getPublishedDate(); result = new SimpleDateFormat("yyyyMMdd").parse("20170522");
            entry2.getPublishedDate(); result = new SimpleDateFormat("yyyyMMdd").parse("20170526");
        }};
        
        feedNewsSourceRetriever.excludeCandidates(entries);
        assertThat(entries.size(), is(1));
        assertThat(entries.get(0), is(entry1));
    }

    @Test
    public void buildSyndFeedByUrl(@Mocked SyndFeed syndFeed,
                                   @Mocked SyndFeedInput input, 
                                   @Mocked XmlReader reader, 
                                   @Mocked URL url) throws Exception {
        new Expectations() {{
            generator.generate(); result = url;
            new XmlReader(url); result = reader;
            new SyndFeedInput(); result = input;
            input.build(reader); result = syndFeed;
        }};
        
        feedNewsSourceRetriever.build();
    }

    @Test
    public void retrieveArticleEntryBySyndFeed(@Mocked SyndFeed feed, 
                                               @Mocked List<SyndEntry> entries,
                                               @Mocked List<ArticleEntry> articleEntries) throws Exception {
        new Expectations(feedNewsSourceRetriever) {{
            feedNewsSourceRetriever.build(); result = feed;
            feed.getEntries(); result = entries;
            feedNewsSourceRetriever.excludeCandidates(entries); result = any;
            feedNewsSourceRetriever.process(feed); result = articleEntries;
        }};
        
        assertThat(feedNewsSourceRetriever.retrieve(), is(articleEntries));
    }
}