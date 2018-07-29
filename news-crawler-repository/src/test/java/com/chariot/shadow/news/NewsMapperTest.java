package com.chariot.shadow.news;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.supplier.*;
import com.chariot.shadow.tag.TagType;
import mockit.*;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Trung Vu on 2017/05/28.
 */
public class NewsMapperTest {

    private NewsMapper newsMapper;
    private SupplierType supplierType = SupplierType.SKY_NEWS;
    
    @Before
    public void setUp() throws Exception {
        newsMapper = new NewsMapper(supplierType);
    }

    @Test
    public void mapArticleEntryToNews(@Mocked ArticleEntry entity) throws Exception {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20170528");
        
        new Expectations() {{
            supplierType.getCode(); result = "S";
            entity.getName(); result = "123";
            entity.getTitle(); result = "title";
            entity.getContent(); result = "content";
            entity.getLink(); result = new URL("http://google.com");
            entity.getPublishedDate(); result = date;
            entity.getImage(); result = "image";
            entity.getTag(); result = "Startup";
        }};

        News actual = newsMapper.map(entity);
        assertThat(actual.getIdAsString(), is("S123"));
        assertThat(actual.getTitleAsString(), is("title"));
        assertThat(actual.getContentAsString(), is("content"));
        assertThat(actual.getLinkAsString(), is("http://google.com"));
        assertThat(actual.getPublicationDateAsDate(), is(date));
        assertThat(actual.getSupplier().getIdAsInt(), is(1));
        assertThat(actual.getSupplier().getCodeAsString(), is("S"));
        assertThat(actual.getSupplier().getNameAsString(), is("Sky News"));
    }

    @Test
    public void mapNewsToNewsEntity() throws Exception {
        News news = createTestNews();
        NewsEntity newsEntity = newsMapper.map(news );
        assertThat(newsEntity.getNewsId(), is(news.getIdAsString()));
        assertThat(newsEntity.getTitle(), is(news.getTitleAsString()));
        assertThat(newsEntity.getContent(), is(news.getContentAsString()));
        assertThat(newsEntity.getLink(), is(news.getLinkAsString()));
        assertThat(newsEntity.getPublishDate(), is(news.getPublicationDateAsDate()));
    }

    private News createTestNews() throws MalformedURLException, ParseException {
        return new News(
                new NewsID("123"),
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
                TagType.START_UP
        );
    }
}