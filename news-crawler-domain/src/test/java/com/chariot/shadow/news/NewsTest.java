package com.chariot.shadow.news;

import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.supplier.SupplierCode;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.SupplierName;
import com.chariot.shadow.tag.TagType;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class NewsTest {

    private News news;

    public NewsTest() throws MalformedURLException, ParseException {
        news = createTestNews();
    }

    @Test
    public void getIdAsString() throws Exception {
        assertThat(news.getIdAsString(), is("id"));
    }

    @Test
    public void getTitleAsString() throws Exception {
        assertThat(news.getTitleAsString(), is("title"));
    }

    @Test
    public void getContentAsString() throws Exception {
        assertThat(news.getContentAsString(), is("content"));
    }

    @Test
    public void getLinkAsString() throws Exception {
        assertThat(news.getLinkAsString(), is("http://google.com"));
    }

    @Test
    public void getPublishedDateAsDate() throws Exception {
        assertThat(news.getPublicationDateAsDate(), is(new SimpleDateFormat("yyyyMMdd").parse("201705267")));
    }

    @Test
    public void getSupplierIdAsString() throws Exception {
        assertThat(news.getSupplierIdAsString(), is("1"));
    }

    private News createTestNews() throws MalformedURLException, ParseException {
        return new News(
                new NewsID("id"),
                new Title("title"),
                new Content("content"),
                new Link(new URL("http://google.com")),
                new PublicationDate(new SimpleDateFormat("yyyyMMdd").parse("201705267")),
                new Supplier(
                        new SupplierID(1),
                        new SupplierCode("Code"),
                        new SupplierName("name")
                ),
                new Image("image"),
                TagType.get(1)
        );
    }
}