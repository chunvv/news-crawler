package com.chariot.shadow.news;

import com.chariot.shadow.news.common.NewsRequester;
import com.sun.syndication.io.FeedException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsApplication {

    private NewsRepository newsRepository = new NewsRepository();


    public List<News> retrieve(File workingFile, NewsRequester newsRequester) throws IOException, FeedException {
        return newsRepository.retrieve(workingFile, newsRequester);
    }

    public void store(List<News> newsList) {
        newsRepository.store(newsList);
    }
}
