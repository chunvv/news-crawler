package com.chariot.shadow.news;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.NewsRequester;
import com.chariot.shadow.news.skynews.ArticleEntry;
import com.chariot.shadow.news.skynews.SkyNewsInfrastructure;
import com.sun.syndication.io.FeedException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NewsRepository to retrieve and write to DISK by calling infrastructure layer
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsRepository {

    private FileInfrastructure fileInfrastructure = new FileInfrastructure();
    private NewsInfrastructure newsInfrastructure = new NewsInfrastructure();
    private NewsMapper newsMapper = new NewsMapper();

    public List<News> retrieve(File workingDirectory, NewsRequester newsRequester) throws IOException, FeedException {
        List<ArticleEntry> newsEntities = new SkyNewsInfrastructure(newsRequester).retrieve(newsRequester);
        newsEntities.forEach(entry -> fileInfrastructure.write(entry, workingDirectory));
        return newsEntities.stream().map(entity -> newsMapper.map(entity)).collect(Collectors.toList());
    }

    public void store(List<News> newsList) {
        newsList.forEach(entity -> newsInfrastructure.insert(newsMapper.map(entity)));
    }
}
