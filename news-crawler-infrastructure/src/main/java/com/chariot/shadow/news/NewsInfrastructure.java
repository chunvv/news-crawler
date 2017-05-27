package com.chariot.shadow.news;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsInfrastructure {

    private static final String NEWS_CRAWLER_PERSISTENCE_UNIT_NAME = "news-crawler";

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(NEWS_CRAWLER_PERSISTENCE_UNIT_NAME);

    public void insert(NewsEntity newsEntity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        NewsEntity existingNews = find(em, newsEntity.getSupplierId(), newsEntity.getNewsId());

        if (existingNews == null) {
            em.persist(newsEntity);
        }

        if (existingNews != null && (existingNews.getPublishDate().compareTo(newsEntity.getPublishDate()) < 1)) {
            existingNews.setTitle(newsEntity.getTitle());
            existingNews.setContent(newsEntity.getContent());
            existingNews.setLink(newsEntity.getLink());
            existingNews.setPublishDate(newsEntity.getPublishDate());
        }

        em.getTransaction().commit();
        em.close();
    }

    public NewsEntity find(EntityManager em, String supplierId, String newsId) {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> query = b.createQuery(NewsEntity.class);
        query.where(newsDetectionPredicates(b, query.from(NewsEntity.class), supplierId, newsId));
        List<NewsEntity> newsEntities = em.createQuery(query).getResultList();
        return newsEntities.size() == 0 ? null : newsEntities.get(0);
    }

    private Predicate[] newsDetectionPredicates(CriteriaBuilder builder, Root<NewsEntity> root, String supplierId, String newsId) {
        return new Predicate[]{
                builder.equal(root.get(NewsEntity_.supplierId), supplierId),
                builder.equal(root.get(NewsEntity_.newsId), newsId)
        };
    }
}
