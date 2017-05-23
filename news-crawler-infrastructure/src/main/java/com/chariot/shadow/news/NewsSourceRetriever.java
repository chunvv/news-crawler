package com.chariot.shadow.news;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public interface NewsSourceRetriever {

    Set<File> retrieve() throws IOException, FeedException;

    SyndFeed build() throws IOException, FeedException;

    void excludeCandidates(List<SyndEntry> entries);

    Set<File> process(SyndFeed feed);
}
