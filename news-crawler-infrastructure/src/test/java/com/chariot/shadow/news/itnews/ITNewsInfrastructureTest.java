package com.chariot.shadow.news.itnews;

import com.chariot.shadow.news.UrlGenerator;
import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsFeedFactory;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
    public void processSyndFeedToArticleList(@Mocked SyndFeed feed,
                                             @Mocked SyndFeedImpl clonedFeed) throws Exception {
        SyndEntry entry1 = new SyndEntryImpl();
        SyndEntry entry2 = new SyndEntryImpl();
        List<SyndEntry> entries = Arrays.asList(entry1, entry2);
        
        new Expectations() {{
            feed.getEntries(); result = entries;
            NewsFeedFactory.createNewsFeed(feed); result = clonedFeed;
        }};
        
        List<ArticleEntry> articleEntries = infrastructure.process(feed);
        assertThat(articleEntries.size(), is(2));
    }
}