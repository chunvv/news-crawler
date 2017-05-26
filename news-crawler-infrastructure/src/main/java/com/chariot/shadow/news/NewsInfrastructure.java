package com.chariot.shadow.news;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsInfrastructure {

    private static final String NEWS_CRAWLER_PERSISTENCE_UNIT_NAME = "news-crawler";

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(NEWS_CRAWLER_PERSISTENCE_UNIT_NAME);

    public void insert(NewsEntity newsEntity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(newsEntity);
        em.getTransaction().commit();
        em.close();
    }
}
