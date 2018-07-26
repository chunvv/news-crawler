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

public class CafefFileInfrastructure extends FileInfrastructure {

    public CafefFileInfrastructure(String directoryPrefix) {
        this.directoryPrefix = directoryPrefix;
    }

    protected void fetchData(File entryDir, URL link) throws NewsRetrieverException, IOException {
        Document doc = Jsoup.connect(link.toString()).get();
        Element title, content;
        if (link.getHost().endsWith("https://kinhdoanh.vnexpress.net/")) {
            title = doc.select(".title_news_detail .mb10").first();
            content = doc.select(".content_detail .fck_detail .width_common .block_ads_connect").first();
        } else {
            title = doc.select(".title_detail").first();
            content = doc.select(".width_common .fck_detail").first();
        }

        if (title == null || content == null)
            throw new NewsRetrieverException("Title or content is null!" + link);

        content.tagName("div");
        Document newsDoc = Jsoup.parseBodyFragment(content.outerHtml());
        newsDoc.head().appendElement("title").text(title.text());
        newsDoc.select("html").attr("baseUri", doc.baseUri());
        FileUtils.write(new File(entryDir, FILE_NAME), newsDoc.outerHtml(), "UTF-8");
    }
}
