package com.chariot.shadow.news.diamond;

import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Diamond Online News Infrastructure
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
public class DiamondNewsInfrastructure extends FeedNewsSourceRetriever {

    public DiamondNewsInfrastructure(NewsRequester newsRequester) {
        super(new DiamondNewsUrlGenerator(), newsRequester);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> process(SyndFeed feed) {
        return (List<Article>) feed.getEntries().
                stream().
                map(entry -> new Article(NewsFeedFactory.createNewsFeed(feed), (SyndEntry) entry)).
                collect(Collectors.toList());
    }
}
