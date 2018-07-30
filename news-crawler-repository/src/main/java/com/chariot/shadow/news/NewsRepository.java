package com.chariot.shadow.news;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.vnexpress.VNExpressfFileInfrastructure;
import com.chariot.shadow.news.vnexpress.VNExpressfNewsInfrastructure;
import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.FeedNewsSourceRetriever;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.news.itnews.ITNewsInfrastructure;
import com.chariot.shadow.news.skynews.SkyNewsInfrastructure;
import com.chariot.shadow.news.vnexpress.business.VNExpressBusinessFileInfrastructure;
import com.chariot.shadow.news.vnexpress.business.VNExpressBusinessNewsInfrastructure;
import com.chariot.shadow.news.vnexpress.science.VNExpressScienceFileInfrastructure;
import com.chariot.shadow.news.vnexpress.science.VNExpressScienceNewsInfrastructure;
import com.chariot.shadow.news.vtv.VtvTechnologyFileInfrastructure;
import com.chariot.shadow.news.vtv.VtvTechnologyNewsInfrastructure;
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
        this.fileInfrastructure = genereateFileInfrastructure();
        this.newsInfrastructure = new NewsInfrastructure();
    }

    public List<News> retrieve(File workingDirectory) throws IOException, FeedException {
        FeedNewsSourceRetriever newsRetriever = generateNewsRetriever();
        List<ArticleEntry> newsEntities = newsRetriever != null ? newsRetriever.retrieve() : Collections.emptyList();
        newsEntities.forEach(entry -> fileInfrastructure.write(entry, workingDirectory));
        return newsEntities.stream().filter(entity -> entity.getEntry() != null).map(entity -> newsMapper.map(entity)).collect(Collectors.toList());
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
            case VN_EXPRESS:
                return new VNExpressfNewsInfrastructure(newsRequester);
            case VN_EXPRESS_BUSINESS:
                return new VNExpressBusinessNewsInfrastructure(newsRequester);
            case VN_EXPRESS_SCIENCE:
                return new VNExpressScienceNewsInfrastructure(newsRequester);
            case VTV_TECHNOLOGY:
                return new VtvTechnologyNewsInfrastructure(newsRequester);
            default:
                return null;
        }
    }

    private FileInfrastructure genereateFileInfrastructure() {
        switch (supplierType) {
            case VN_EXPRESS:
                return new VNExpressfFileInfrastructure(supplierType.getCode());
            case VN_EXPRESS_BUSINESS:
                return new VNExpressBusinessFileInfrastructure(supplierType.getCode());
            case VN_EXPRESS_SCIENCE:
                return new VNExpressScienceFileInfrastructure(supplierType.getCode());
            case VTV_TECHNOLOGY:
                return new VtvTechnologyFileInfrastructure(supplierType.getCode());
            default:
                return null;
        }
    }
}
