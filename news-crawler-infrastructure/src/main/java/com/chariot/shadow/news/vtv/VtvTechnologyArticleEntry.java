package com.chariot.shadow.news.vtv;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VtvTechnologyArticleEntry extends ArticleEntry {

    private static final Pattern UNIQUE_PATTERN = Pattern.compile("[^/-]*$");

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

    public String generateUniqueFileName() throws NewsRetrieverException {
        String guId =
                Optional.
                        of(getEntry().getLink().replace(".htm", "")).
                        orElseThrow(() -> new NewsRetrieverException("No guId element in:" + getEntry().getTitle()));

        Matcher matcher = UNIQUE_PATTERN.matcher(guId);
        if (!matcher.find())
            throw new NewsRetrieverException("Can not get file name by unique pattern, maybe guId pattern changed: " + guId);

        return matcher.group();
    }
}
