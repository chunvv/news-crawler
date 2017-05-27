package com.chariot.shadow.news.factory;

import com.chariot.shadow.news.*;
import com.chariot.shadow.supplier.Supplier;
import mockit.Mocked;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/05/27.
 */
public class NewsFactoryTest {

    @Test
    public void createNews(@Mocked NewsID id, @Mocked Title title,
                           @Mocked Content content, @Mocked Link link,
                           @Mocked PublicationDate publicationDate,
                           @Mocked Supplier supplier) throws Exception {
        News news = NewsFactory.create(id, title, content, link, publicationDate, supplier);

        assertThat(news.getId(), is(id));
        assertThat(news.getTitle(), is(title));
        assertThat(news.getContent(), is(content));
        assertThat(news.getLink(), is(link));
        assertThat(news.getPublicationDateAsDate(), is(publicationDate.getDate()));
        assertThat(news.getSupplier(), is(supplier));
    }
}