package com.chariot.shadow.news.vtv;

import com.chariot.shadow.news.common.ArticleEntry;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.Date;

public class VtvTechnologyArticleEntry extends ArticleEntry {

    public VtvTechnologyArticleEntry(SyndFeed feed, SyndEntry entry) {
        super(feed, entry);
    }

    @Override
    public String getContent() {
        String content = getEntry().getDescription().getValue();
        return content.substring(content.indexOf("VTV.vn - ") + 9);
    }

    @Override
    public String getImage() {
        return getEntry().getDescription().getValue().split("\"")[3];
    }

    @Override
    public String getTag() {
        return "Technology";
    }

    @Override
    public Date getPublishedDate() {
        return getEntry().getPublishedDate() == null ? new Date() : getEntry().getPublishedDate();
    }
}
