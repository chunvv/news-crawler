package com.chariot.shadow.news.itnews;

import com.chariot.shadow.news.UrlGenerator;
import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/28.
 */
public class ITNewsInfrastructureTest {

    @Tested
    private ITNewsInfrastructure infrastructure;
    @Injectable
    private UrlGenerator generator;
    @Injectable
    private NewsRequester newsRequester;

    @Test
    public void processSyndFeedToArticleList() throws Exception {
        SyndFeed originalFeed = createOriginalFeed();

        List<ArticleEntry> articleEntries = infrastructure.process(originalFeed);
        assertThat(articleEntries.size(), is(2));
    }

    private SyndFeed createOriginalFeed() {
        SyndFeed feed = new SyndFeedImpl();
        feed.setTitle("original");
        List<SyndEntry> entries = new ArrayList<>();
        entries.add(new SyndEntryImpl());
        entries.add(new SyndEntryImpl());
        feed.setEntries(entries);
        return feed;
    }
}