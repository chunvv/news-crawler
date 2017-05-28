package com.chariot.shadow.news;

import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.supplier.SupplierType;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Trung Vu on 2017/05/28.
 */
public class NewsApplicationTest {

    private NewsApplication newsApplication;
    private SupplierType supplierType;
    private NewsRequester newsRequester;
    private NewsRepository newsRepository;
    
    @Before
    public void setUp() throws Exception {
        supplierType = SupplierType.SKY_NEWS;
        Date from = new SimpleDateFormat("yyyyMMdd").parse("20170525");
        Date to = new SimpleDateFormat("yyyyMMdd").parse("20170528");
        newsRequester = new NewsRequester() {
            @Override
            public Date from() {
                return from;
            }

            @Override
            public Date to() {
                return to;
            }
        };

        newsApplication = new NewsApplication(supplierType, newsRequester);
        newsRepository = new NewsRepository(supplierType, newsRequester);
        Deencapsulation.setField(newsApplication, "newsRepository", newsRepository);
    }

    @Test
    public void storeNewsListToRepository(@Mocked News news) throws Exception {
        new Expectations(newsRepository) {{
            newsRepository.store(asList(news)); result = any;
        }};

        newsApplication.store(asList(news));
    }

    @Test
    public void retrieveNewsFromRepository(@Mocked File workingFile, @Mocked News news) throws Exception {
        new Expectations(newsRepository) {{
            newsRepository.retrieve(workingFile); result = asList(news);
        }};
        
        List<News> actualNewsList = newsApplication.retrieve(workingFile);
        assertThat(actualNewsList.size(), is(1));
        assertThat(actualNewsList.get(0), is(news));
    }
}