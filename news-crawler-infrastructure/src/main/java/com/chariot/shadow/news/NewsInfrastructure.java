package com.chariot.shadow.news;

import javax.persistence.EntityManager;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsInfrastructure {

    private EntityManager entityManager;

    public void insert(News news) {
        entityManager.persist(news);
    }
}
