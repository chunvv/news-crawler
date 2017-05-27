package com.chariot.shadow.news;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.news.itnews.ITNewsInfrastructure;
import com.chariot.shadow.news.skynews.SkyNewsInfrastructure;
import com.chariot.shadow.supplier.SupplierType;
import com.sun.syndication.io.FeedException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NewsRepository to retrieve and write to DISK by calling infrastructure layer
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsRepository {

    private SupplierType supplierType;
    private NewsRequester newsRequester;
    private FileInfrastructure fileInfrastructure;
    private NewsInfrastructure newsInfrastructure;
    private NewsMapper newsMapper;

    public NewsRepository(SupplierType supplierType, NewsRequester newsRequester) {
        this.supplierType = supplierType;
        this.newsRequester = newsRequester;
        this.newsMapper = new NewsMapper(supplierType);
        this.fileInfrastructure = new FileInfrastructure(supplierType.getCode());
        this.newsInfrastructure = new NewsInfrastructure();
    }

    public List<News> retrieve(File workingDirectory) throws IOException, FeedException {
        FeedNewsSourceRetriever newsRetriever = generateNewsRetriever();
        List<ArticleEntry> newsEntities = newsRetriever != null ? newsRetriever.retrieve() : Collections.emptyList();
        newsEntities.forEach(entry -> fileInfrastructure.write(entry, workingDirectory));
        return newsEntities.stream().map(entity -> newsMapper.map(entity)).collect(Collectors.toList());
    }

    public void store(List<News> newsList) {
        newsList.forEach(entity -> newsInfrastructure.insert(newsMapper.map(entity)));
    }

    private FeedNewsSourceRetriever generateNewsRetriever() {
        switch (supplierType) {
            case SKY_NEWS:
                return new SkyNewsInfrastructure(newsRequester);
            case IT_NEWS:
                return new ITNewsInfrastructure(newsRequester);
            default:
                return null;
        }
    }
}
