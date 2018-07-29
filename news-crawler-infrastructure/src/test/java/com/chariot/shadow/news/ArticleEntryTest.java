package com.chariot.shadow.news;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Trung Vu on 2017/05/23.
 */
public class ArticleEntryTest {

    @Tested
    private ArticleEntry news;
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

        assertThat(news.getName(), is("129212"));

    }

    @Test(expected = NewsRetrieverException.class)
    public void throwExceptionWhenGuIdIsInvalid() throws Exception {
        new Expectations(news) {{
            news.getEntry(); result = entry;
            entry.getUri(); result = null;
        }};

        news.getName();
        fail();
    }

    @Test
    public void getEntry(@Mocked List<SyndEntry> entries, @Mocked SyndEntry mockedEntry) throws Exception {
        new Expectations() {{
            feed.getEntries(); result = entries;
            entries.get(0); result = mockedEntry;
        }};

        assertThat(news.getEntry(), is(mockedEntry));
    }

    @Test
    public void getTitle(@Mocked SyndEntry mockedEntry) throws Exception {
        new Expectations(news) {{
            news.getEntry(); result = mockedEntry;
            mockedEntry.getTitle(); result = "title";
        }};

        assertThat(news.getTitle(), is("title"));
    }

    @Test
    public void getContent(@Mocked SyndEntry mockedEntry, @Mocked SyndContent content) throws Exception {
        new Expectations(news) {{
            news.getEntry(); result = mockedEntry;
            mockedEntry.getDescription().getValue(); result = "<a href=\"http://startup.vnexpress.net/tin-tuc/y-tuong-moi/nhom-sinh-vien-harvard-bo-hoc-lap-startup-ket-ban-ngoai-doi-thuc-3783143.html\"><img width=130 height=100 src=\"https://i-startup.vnecdn.net/2018/07/25/https-2F-2Fblogs-images-forbes-6479-7891-1532516954_180x108.jpg\" ></a></br>Ba đồng sáng lập đều có thành tích xuất sắc tại Harvard quyết định bỏ học giữa chừng, thành lập nền tảng kết bạn trực tuyến.";
        }};

        assertThat(news.getContent(), is("Ba đồng sáng lập đều có thành tích xuất sắc tại Harvard quyết định bỏ học giữa chừng, thành lập nền tảng kết bạn trực tuyến."));
    }

    @Test
    public void getLink(@Mocked SyndEntry mockedEntry) throws Exception {
        new Expectations(news) {{
            news.getEntry(); result = mockedEntry;
            mockedEntry.getLink(); result = "http://google.com";
        }};

        assertThat(news.getLink(), is(new URL("http://google.com")));
    }

    @Test
    public void getPublishedDate(@Mocked SyndEntry mockedEntry) throws Exception {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20170527");
        new Expectations(news) {{
            news.getEntry(); result = mockedEntry;
            mockedEntry.getPublishedDate(); result = date;
        }};

        assertThat(news.getPublishedDate(), is(date));
    }

    @Test
    public void getImage(@Mocked SyndEntry mockedEntry) throws Exception {
        String source = "<a href=\"http://startup.vnexpress.net/tin-tuc/y-tuong-moi/nhom-sinh-vien-harvard-bo-hoc-lap-startup-ket-ban-ngoai-doi-thuc-3783143.html\"><img width=130 height=100 src=\"https://i-startup.vnecdn.net/2018/07/25/https-2F-2Fblogs-images-forbes-6479-7891-1532516954_180x108.jpg\" ></a></br>Ba đồng sáng lập đều có thành tích xuất sắc tại Harvard quyết định bỏ học giữa chừng, thành lập nền tảng kết bạn trực tuyến.";
        new Expectations(news) {{
            news.getEntry(); result = mockedEntry;
            mockedEntry.getDescription().getValue(); result = source;
        }};

        assertThat(news.getImage(), is("https://i-startup.vnecdn.net/2018/07/25/https-2F-2Fblogs-images-forbes-6479-7891-1532516954_180x108.jpg"));
    }
}