package com.chariot.shadow.news.common;

import com.chariot.shadow.news.diamond.NewsEntity;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public interface NewsSourceRetriever {

    List<NewsEntity> retrieve() throws IOException, FeedException;

    SyndFeed build() throws IOException, FeedException;

    void excludeCandidates(List<SyndEntry> entries);

    List<NewsEntity> process(SyndFeed feed);
}
