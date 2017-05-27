package com.chariot.shadow.news;

import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.supplier.SupplierType;
import com.sun.syndication.io.FeedException;
import lombok.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@Value
public class NewsApplication {

    private SupplierType supplier;
    private NewsRequester newsRequester;
    private NewsRepository newsRepository;

    public NewsApplication(SupplierType supplier, NewsRequester newsRequester) {
        this.supplier = supplier;
        this.newsRequester = newsRequester;
        this.newsRepository = new NewsRepository(supplier, newsRequester);
    }

    public List<News> retrieve(File workingFile) throws IOException, FeedException {
        return newsRepository.retrieve(workingFile);
    }

    public void store(List<News> newsList) {
        newsRepository.store(newsList);
    }
}