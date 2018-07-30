package com.chariot.shadow.news.vnexpress.science;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsFeedFactory;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.news.vnexpress.business.VNExpressBusinessArticleEntry;
import com.chariot.shadow.news.vnexpress.business.VNExpressBusinessNewsUrlGenerator;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sky News Infrastructure
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
public class VNExpressScienceNewsInfrastructure extends FeedNewsSourceRetriever {

    public VNExpressScienceNewsInfrastructure(NewsRequester newsRequester) {
        super(new VNExpressScienceNewsUrlGenerator(), newsRequester);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleEntry> process(SyndFeed feed) {
        return (List<ArticleEntry>) feed.getEntries().
                stream().
                map(entry -> new VNExpressScienceArticleEntry(NewsFeedFactory.createNewsFeed(feed), (SyndEntry) entry)).
                collect(Collectors.toList());
    }
}
