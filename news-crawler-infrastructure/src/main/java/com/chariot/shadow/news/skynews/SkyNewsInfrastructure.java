package com.chariot.shadow.news.skynews;

import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sky News Infrastructure
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
public class SkyNewsInfrastructure extends FeedNewsSourceRetriever {

    public SkyNewsInfrastructure(NewsRequester newsRequester) {
        super(new SkyNewsUrlGenerator(), newsRequester);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleEntry> process(SyndFeed feed) {
        return (List<ArticleEntry>) feed.getEntries().
                stream().
                map(entry -> new ArticleEntry(NewsFeedFactory.createNewsFeed(feed), (SyndEntry) entry)).
                collect(Collectors.toList());
    }
}
