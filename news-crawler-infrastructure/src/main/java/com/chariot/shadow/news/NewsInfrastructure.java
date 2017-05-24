package com.chariot.shadow.news;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@Named
public class NewsInfrastructure {

    @Inject
    private EntityManager entityManager;

    public void insert(News news) {
        entityManager.persist(news);
    }
}
