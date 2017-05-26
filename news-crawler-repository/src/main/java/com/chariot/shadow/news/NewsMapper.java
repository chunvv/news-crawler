package com.chariot.shadow.news;

import com.chariot.shadow.news.common.NewsRetrieverException;
import com.chariot.shadow.news.diamond.Article;
import com.chariot.shadow.news.factory.NewsFactory;
import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.supplier.SupplierCode;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.SupplierName;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import static com.chariot.shadow.supplier.SupplierType.DIAMOND;

/**
 * Mapping Article to News object
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsMapper {

    public News map(Article entity) {
        try {
            return NewsFactory.create(
                    new NewsID(entity.generateUniqueFileName()),
                    new Title(entity.getEntry().getTitle()),
                    new Content(entity.getEntry().getDescription().getValue()),
                    new Link(new URL(entity.getEntry().getLink())),
                    new PublicationDate(entity.getEntry().getPublishedDate()),
                    new Supplier(
                            new SupplierID(DIAMOND.getId()),
                            new SupplierCode(DIAMOND.getCode()),
                            new SupplierName(DIAMOND.getName()))
            );
        } catch (NewsRetrieverException | MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public NewsEntity map(News news) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setNewsId(news.getId().getNewsID());
        newsEntity.setSupplierId(String.valueOf(news.getSupplier().getId().getId()));
        newsEntity.setTitle(news.getTitle().getTitle());
        newsEntity.setContent(news.getContent().getContent());
        newsEntity.setLink(String.valueOf(news.getLink().getLink()));
        newsEntity.setPublishDate(news.getPublicationDate().getDate());
        newsEntity.setRegistrationTimestamp(new Timestamp(System.currentTimeMillis()));
        return newsEntity;
    }
}
