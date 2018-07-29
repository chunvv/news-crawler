package com.chariot.shadow.news.vtv;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.NewsRetrieverException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VtvTechnologyFileInfrastructure extends FileInfrastructure {

    public VtvTechnologyFileInfrastructure(String directoryPrefix) {
        this.directoryPrefix = directoryPrefix;
    }

    protected void fetchData(File entryDir, URL link) throws NewsRetrieverException, IOException {
        Document doc = Jsoup.connect(link.toString()).get();
        Element news, content;
        news = doc.select(".clearfix.inner").first();
        content = doc.select(".ta-justify").first();
        if (content == null)
            throw new NewsRetrieverException("Content is null!" + link);

        if (news != null) {
            news.append(content.toString());
            news.tagName("div");
            FileUtils.write(new File(entryDir, FILE_NAME), news.toString(), "UTF-8");
        } else {
            content.tagName("div");
            FileUtils.write(new File(entryDir, FILE_NAME), content.toString(), "UTF-8");
        }
    }
}
