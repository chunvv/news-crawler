package com.chariot.shadow.news.cafef;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.NewsRetrieverException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VNExpressfFileInfrastructure extends FileInfrastructure {

    public VNExpressfFileInfrastructure(String directoryPrefix) {
        this.directoryPrefix = directoryPrefix;
    }

    protected void fetchData(File entryDir, URL link) throws NewsRetrieverException, IOException {
        Document doc = Jsoup.connect(link.toString()).get();
        Element content;
        if (link.getHost().endsWith("https://kinhdoanh.vnexpress.net/")) {
            content = doc.select(".content_detail .fck_detail .width_common .block_ads_connect").first();
        } else {
            content = doc.select(".width_common .fck_detail").first();
        }

        if (content == null)
            throw new NewsRetrieverException("Content is null!" + link);

        content.tagName("div");
        FileUtils.write(new File(entryDir, FILE_NAME), content.toString(), "UTF-8");
    }
}
