package com.chariot.shadow.news.common;

import com.chariot.shadow.news.UrlGenerator;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/23.
 */
@AllArgsConstructor
@Getter
public abstract class FeedNewsSourceRetriever implements NewsSourceRetriever {

    protected UrlGenerator generator;
    protected NewsRequester newsRequester;

    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleEntry> retrieve(NewsRequester newsRequester) throws IOException, FeedException {
        this.newsRequester = newsRequester;
        SyndFeed feed = build();
        excludeCandidates(feed.getEntries());
        return process(feed);
    }

    @Override
    public SyndFeed build() throws IOException, FeedException {
        return new SyndFeedInput().build(new XmlReader(generator.generate()));
    }

    @Override
    public void excludeCandidates(List<SyndEntry> entries) {
        Date from = newsRequester.from();
        Date to = newsRequester.to();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            SyndEntry entry = (SyndEntry) iterator.next();
            Date publishedDate = entry.getPublishedDate();
            if (publishedDate != null && (publishedDate.compareTo(from) < 0 || publishedDate.compareTo(to) > 0)) {
                iterator.remove();
            }
        }
    }
}
