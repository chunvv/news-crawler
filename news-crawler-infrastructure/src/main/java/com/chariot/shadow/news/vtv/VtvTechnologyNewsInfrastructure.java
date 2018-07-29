package com.chariot.shadow.news.vtv;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsFeedFactory;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.news.vnexpress.VNExpressfNewsUrlGenerator;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sky News Infrastructure
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
public class VtvTechnologyNewsInfrastructure extends FeedNewsSourceRetriever {

    public VtvTechnologyNewsInfrastructure(NewsRequester newsRequester) {
        super(new VtvTechnologyNewsUrlGenerator(), newsRequester);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleEntry> process(SyndFeed feed) {
        return (List<ArticleEntry>) feed.getEntries().
                stream().
                map(entry -> new VtvTechnologyArticleEntry(NewsFeedFactory.createNewsFeed(feed), (SyndEntry) entry)).
                collect(Collectors.toList());
    }
}
