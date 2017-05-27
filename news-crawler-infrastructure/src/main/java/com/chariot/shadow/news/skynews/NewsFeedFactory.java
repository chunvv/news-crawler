package com.chariot.shadow.news.skynews;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * Cloning a original feed to a new feed by getting some important fields
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
public class NewsFeedFactory {

    public static SyndFeed createNewsFeed(SyndFeed original) {
        SyndFeed result = new SyndFeedImpl();
        result.setFeedType(original.getFeedType());
        result.setTitle(original.getTitle());
        result.setDescription(original.getDescription());
        result.setLink(original.getLink());
        result.setAuthor(original.getAuthor());
        result.setEncoding(original.getEncoding() == null ? "UTF-8" : original.getEncoding());
        return result;
    }
}
