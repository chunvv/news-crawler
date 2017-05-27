package com.chariot.shadow.news.itnews;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsFeedFactory;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * IT News Infrastructure
 * <p>
 * Created by Trung Vu on 2017/05/27.
 */
public class ITNewsInfrastructure extends FeedNewsSourceRetriever {

    public ITNewsInfrastructure(NewsRequester newsRequester) {
        super(new ITNewsUrlGenerator(), newsRequester);
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
