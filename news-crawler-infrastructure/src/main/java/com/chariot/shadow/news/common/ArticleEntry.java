package com.chariot.shadow.news.common;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Article as a object of rss feed for aa item
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
@AllArgsConstructor
@Getter
public class ArticleEntry {

    private static final Pattern UNIQUE_PATTERN = Pattern.compile("[^/-]*$");
    protected SyndFeed feed;

    public ArticleEntry(SyndFeed feed, SyndEntry entry) {
        this.feed = feed;
        this.feed.setEntries(Arrays.asList(entry));
    }

    public String generateUniqueFileName() throws NewsRetrieverException {
        String guId =
                Optional.
                        ofNullable(getEntry().getUri()).
                        orElseThrow(() -> new NewsRetrieverException("No guId element in:" + getEntry().getTitle())).replace(".html", "");

        Matcher matcher = UNIQUE_PATTERN.matcher(guId);
        if (!matcher.find())
            throw new NewsRetrieverException("Can not get file name by unique pattern, maybe guId pattern changed: " + guId);

        return matcher.group();
    }

    public SyndEntry getEntry() {
        return (SyndEntry) feed.getEntries().get(0);
    }

    public String getName() throws NewsRetrieverException {
        return generateUniqueFileName();
    }

    public String getTitle() {
        return getEntry().getTitle();
    }

    public String getContent() {
        String content = getEntry().getDescription().getValue();
        return content.substring(content.indexOf("</br>") + 5);
    }

    public URL getLink() throws MalformedURLException {
        return new URL(getEntry().getLink());
    }

    public Date getPublishedDate() {
        return getEntry().getPublishedDate();
    }

    public String getImage() {
        return getEntry().getDescription().getValue().split("\"")[3];
    }

    public String getTag() {
        return "Startup";
    }
}
