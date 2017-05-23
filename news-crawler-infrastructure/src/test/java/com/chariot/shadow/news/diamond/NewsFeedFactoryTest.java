package com.chariot.shadow.news.diamond;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class NewsFeedFactoryTest {

    @Test
    public void cloneNewsFeed() throws Exception {
        SyndFeed newsFeed = NewsFeedFactory.createNewsFeed(createTestFeed());

        assertThat(newsFeed.getFeedType(), is("type"));
        assertThat(newsFeed.getTitle(), is("title"));
        assertThat(newsFeed.getDescription(), is("description"));
        assertThat(newsFeed.getLink(), is("link"));
        assertThat(newsFeed.getAuthor(), is("author"));
        assertThat(newsFeed.getEncoding(), is("UTF-8"));
    }

    private SyndFeed createTestFeed() {
        SyndFeed feed = new SyndFeedImpl();

        feed.setFeedType("type");
        feed.setTitle("title");
        feed.setDescription("description");
        feed.setLink("link");
        feed.setAuthor("author");
        feed.setEncoding("UTF-8");

        return feed;
    }
}