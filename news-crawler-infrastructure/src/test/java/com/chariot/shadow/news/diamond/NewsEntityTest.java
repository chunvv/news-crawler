package com.chariot.shadow.news.diamond;

import com.chariot.shadow.news.NewsRetrieverException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class NewsEntityTest {

    @Tested
    private NewsEntity news;
    @Injectable
    private SyndFeed feed;
    @Injectable 
    private SyndEntry entry;

    @Test
    public void generateUniqueFileName() throws Exception {

        new Expectations(news) {{
            news.getEntry(); result = entry;
            entry.getUri(); result = "http://diamond.jp/articles/-/129212";
        }};

        assertThat(news.generateUniqueFileName(), Is.is("D129212"));
    }

    @Test(expected = NewsRetrieverException.class)
    public void throwExceptionWhenGuIdIsInvalid() throws Exception {
        new Expectations(news) {{
            news.getEntry(); result = entry;
            entry.getUri(); result = null;
        }};

        assertThat(news.generateUniqueFileName(), Is.is("D129212"));
    }

    @Test
    public void getEntries(@Mocked List<SyndEntry> entries, @Mocked SyndEntry mockedEntry) throws Exception {
        new Expectations() {{
            feed.getEntries(); result = entries;
            entries.get(0); result = mockedEntry;
        }};

        assertThat(news.getEntry(), Is.is(mockedEntry));
    }
}