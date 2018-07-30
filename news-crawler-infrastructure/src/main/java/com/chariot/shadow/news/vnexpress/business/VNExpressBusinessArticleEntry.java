package com.chariot.shadow.news.vnexpress.business;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;

public class VNExpressBusinessArticleEntry extends ArticleEntry {

    public VNExpressBusinessArticleEntry(SyndFeed feed, SyndEntry entry) {
        super(feed, entry);
    }

    @Override
    public String getContent() {
        String content = getEntry().getDescription().getValue();
        return content.substring(content.indexOf("</br>") + 5);
    }

    @Override
    public String getImage() {
        return getEntry().getDescription().getValue().split("\"")[3];
    }

    @Override
    public String getTag() {
        return "Business";
    }

    @Override
    public Date getPublishedDate() {
        return getEntry().getPublishedDate() == null ? new Date() : getEntry().getPublishedDate();
    }

    public String generateUniqueFileName() throws NewsRetrieverException {
        String guId =
                Optional.
                        of(getEntry().getUri().replace(".html", "")).
                        orElseThrow(() -> new NewsRetrieverException("No guId element in:" + getEntry().getTitle()));

        Matcher matcher = UNIQUE_PATTERN.matcher(guId);
        if (!matcher.find())
            throw new NewsRetrieverException("Can not get file name by unique pattern, maybe guId pattern changed: " + guId);

        return matcher.group();
    }
}
