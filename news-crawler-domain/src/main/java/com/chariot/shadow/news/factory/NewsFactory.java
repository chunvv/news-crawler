package com.chariot.shadow.news.factory;

import com.chariot.shadow.news.*;
import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.tag.TagType;

/**
 * Creating new instance of News
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsFactory {

    public static News create(NewsID id, Title title, Content content, Link link, PublicationDate publicationDate, Supplier supplier, Image image, TagType tagType) {
        return new News(id, title, content, link, publicationDate, supplier, image, tagType);
    }
}
