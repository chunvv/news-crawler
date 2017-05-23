package com.chariot.shadow.news.diamond;

import com.chariot.shadow.news.FeedNewsSourceRetriever;
import com.chariot.shadow.news.NewsRequester;
import com.sun.syndication.feed.synd.SyndFeed;
import lombok.Value;

import java.io.File;
import java.util.Set;

/**
 * Created by Trung Vu on 2017/05/23.
 */
@Value
public class DiamondNewsInfrastructure extends FeedNewsSourceRetriever {

    public DiamondNewsInfrastructure(NewsRequester newsRequester) {
        super(new DiamondNewsUrlGenerator(), newsRequester);
    }

    @Override
    public Set<File> process(SyndFeed feed) {
        return null;
    }
}
