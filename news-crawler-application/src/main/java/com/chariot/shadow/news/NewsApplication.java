package com.chariot.shadow.news;

import com.sun.syndication.io.FeedException;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@Named
public class NewsApplication {

    @Inject
    private NewsRepository newsRepository;

    public List<News> retrieve(File workingFile) throws IOException, FeedException {
        return newsRepository.retrieve(workingFile);
    }

    public void store(List<News> newsList) {
        newsRepository.store(newsList);
    }
}
