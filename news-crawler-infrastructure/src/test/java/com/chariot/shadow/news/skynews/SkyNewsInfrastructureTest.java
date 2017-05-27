package com.chariot.shadow.news.skynews;

import com.chariot.shadow.news.UrlGenerator;
import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class SkyNewsInfrastructureTest {

    @Tested
    private SkyNewsInfrastructure infrastructure;
    @Injectable
    private UrlGenerator generator;
    @Injectable
    private NewsRequester newsRequester;

    @Test
    public void processCrawler() throws Exception {
        List<SyndEntry> entries = createTestEntries();
        SyndFeed feed = createTestFeed();
        
        new Expectations(feed) {{
            feed.getEntries(); result = entries;
        }};

        List<ArticleEntry> actual = infrastructure.process(feed);
        
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getFeed().getAuthor(), is("author"));
        assertThat(actual.get(0).getEntry().getAuthor(), is("1"));
        assertThat(actual.get(1).getFeed().getAuthor(), is("author"));
        assertThat(actual.get(1).getEntry().getAuthor(), is("2"));
    }

    private SyndFeed createTestFeed() {
        SyndFeed feed = new SyndFeedImpl();
        feed.setAuthor("author");
        feed.setDescription("sample description");
        return feed;
    }

    private List<SyndEntry> createTestEntries() {
        SyndEntry entry1 = new SyndEntryImpl();
        entry1.setAuthor("1");
        SyndEntry entry2 = new SyndEntryImpl();
        entry2.setAuthor("2");

        return Arrays.asList(entry1, entry2);
    }
}