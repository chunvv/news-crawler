package com.chariot.shadow.news.vnexpress.business;

import com.chariot.shadow.file.FileInfrastructure;
import com.chariot.shadow.news.common.NewsRetrieverException;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VNExpressBusinessFileInfrastructure extends FileInfrastructure {

    public VNExpressBusinessFileInfrastructure(String directoryPrefix) {
        this.directoryPrefix = directoryPrefix;
    }

    protected void fetchData(File entryDir, URL link) throws NewsRetrieverException, IOException {
        Document doc = Jsoup.connect(link.toString()).get();
        Element content = doc.select(".content_detail.fck_detail.width_common.block_ads_connect").first();

        if (content == null)
            throw new NewsRetrieverException("Content is null!" + link);

        content.tagName("div");
        FileUtils.write(new File(entryDir, FILE_NAME), content.toString(), "UTF-8");
    }
}
