package com.chariot.shadow.news;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.diamond.DiamondNewsInfrastructure;
import com.chariot.shadow.news.diamond.NewsEntity;
import com.sun.syndication.io.FeedException;

import javax.inject.Inject;
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

    @Inject
    private DiamondNewsInfrastructure diamondNewsInfrastructure;
    @Inject
    private FileInfrastructure fileInfrastructure;
    @Inject
    private NewsInfrastructure newsInfrastructure;
    @Inject
    private NewsMapper mapper;

    public List<News> retrieve(File workingDirectory) throws IOException, FeedException {
        List<NewsEntity> newsEntities = diamondNewsInfrastructure.retrieve();
        newsEntities.forEach(entry -> fileInfrastructure.write(entry, workingDirectory));
        return newsEntities.stream().map(entity -> mapper.map(entity)).collect(Collectors.toList());
    }

    public void store(List<News> newsList) {
        newsList.forEach(entity -> newsInfrastructure.insert(entity));
    }
}
