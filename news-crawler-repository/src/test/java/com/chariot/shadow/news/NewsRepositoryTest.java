package com.chariot.shadow.news;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.supplier.SupplierType;
import mockit.*;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/28.
 */
public class NewsRepositoryTest {

    private NewsRepository newsRepository;
    private NewsRequester newsRequester;
    private FileInfrastructure fileInfrastructure;
    private NewsInfrastructure newsInfrastructure;
    private NewsMapper newsMapper;

    @Before
    public void setUp() throws Exception {
        SupplierType supplierType = SupplierType.SKY_NEWS;
        Date from = new SimpleDateFormat("yyyyMMdd").parse("20170525");
        Date to = new SimpleDateFormat("yyyyMMdd").parse("20170528");
        NewsRequester newsRequester = new NewsRequester() {
            @Override
            public Date from() {
                return from;
            }

            @Override
            public Date to() {
                return to;
            }
        };
        
        newsRepository = new NewsRepository(supplierType, newsRequester);

        newsMapper = new NewsMapper(supplierType);
        Deencapsulation.setField(newsRepository, "newsMapper", newsMapper);
        newsInfrastructure = new NewsInfrastructure();
        Deencapsulation.setField(newsRepository, "newsInfrastructure", newsInfrastructure);
    }

    @Test
    public void storeNewsListToInfrastructure(@Mocked News news, @Mocked NewsEntity newsEntity) throws Exception {
        List<News> newsList = Arrays.asList(news);
        new Expectations(newsMapper, newsInfrastructure) {{
            newsMapper.map(news); result = newsEntity;
            newsInfrastructure.insert(newsEntity); result = any;
        }};
        
        newsRepository.store(newsList);
    }
}