package com.chariot.shadow.news.diamond;

import com.chariot.shadow.news.NewsRetrieverException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import lombok.Value;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Trung Vu on 2017/05/23.
 */
@Value
public class NewsEntity {

    private static final Pattern UNIQUE_PATTERN = Pattern.compile("[^/]*$");
    private static final String FILENAME_PREFIX = "D";
    private SyndFeed feed;

    public NewsEntity(SyndFeed feed, SyndEntry entry) {
        this.feed = feed;
        this.feed.setEntries(Arrays.asList(entry));
    }

    public String generateUniqueFileName() throws NewsRetrieverException {
        String guId =
                Optional.
                        ofNullable(getEntry().getUri()).
                        orElseThrow(() -> new NewsRetrieverException("No guId element in:" + getEntry().getTitle()));

        Matcher matcher = UNIQUE_PATTERN.matcher(guId);
        if (!matcher.find())
            throw new NewsRetrieverException("Can not get file name by unique pattern, maybe guId pattern changed: " + guId);

        return FILENAME_PREFIX + matcher.group();
    }

    public SyndEntry getEntry() {
        return (SyndEntry) feed.getEntries().get(0);
    }
}
