package com.chariot.shadow.news;

import com.chariot.shadow.news.common.ArticleEntry;
import com.chariot.shadow.news.common.NewsRetrieverException;
import com.chariot.shadow.news.factory.NewsFactory;
import com.chariot.shadow.supplier.SupplierType;
import com.chariot.shadow.supplier.factory.SupplierFactory;
import lombok.Value;

import java.net.MalformedURLException;
import java.sql.Timestamp;

/**
 * Mapping ArticleEntry to News object
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
@Value
public class NewsMapper {

    private SupplierType supplierType;

    public News map(ArticleEntry entity) {
        try {
            return NewsFactory.create(
                    new NewsID(supplierType.getCode() + entity.getName()),
                    new Title(entity.getTitle()),
                    new Content(entity.getContent()),
                    new Link(entity.getLink()),
                    new PublicationDate(entity.getPublishedDate()),
                    SupplierFactory.create(supplierType)
            );
        } catch (NewsRetrieverException | MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public NewsEntity map(News news) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setNewsId(news.getIdAsString());
        newsEntity.setSupplierId(news.getSupplierIdAsString());
        newsEntity.setTitle(news.getTitleAsString());
        newsEntity.setContent(news.getContentAsString());
        newsEntity.setLink(news.getLinkAsString());
        newsEntity.setPublishDate(news.getPublicationDateAsDate());
        newsEntity.setRegistrationTimestamp(new Timestamp(System.currentTimeMillis()));
        return newsEntity;
    }
}
