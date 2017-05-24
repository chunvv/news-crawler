package com.chariot.shadow.news;

import com.chariot.shadow.news.diamond.NewsEntity;
import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.supplier.SupplierCode;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.SupplierName;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;

import static com.chariot.shadow.supplier.SupplierType.DIAMOND;

/**
 * Mapping NewsEntity to News object
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
@Named
public class NewsMapper {

    public News map(NewsEntity entity) {
        try {
            return new News(
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
}
