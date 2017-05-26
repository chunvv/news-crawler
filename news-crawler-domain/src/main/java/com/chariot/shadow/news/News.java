package com.chariot.shadow.news;

import com.chariot.shadow.supplier.Supplier;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * News contains some information as id, title, content, link, published data and the supplier is belong to
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
@Value
@AllArgsConstructor
public class News {

    private NewsID id;
    private Title title;
    private Content content;
    private Link link;
    private PublicationDate publicationDate;

    private Supplier supplier;
}
