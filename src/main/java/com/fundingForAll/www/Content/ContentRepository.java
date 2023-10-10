package com.fundingForAll.www.Content;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ContentRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public ContentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public String save(Content content) {
        em.persist(content);
        return content.getId();
    }

    public Optional<Content> findById(String contentId) {
        QContent qContent = QContent.content;

        Content content = queryFactory.
                selectFrom(qContent)
                .where(qContent.id.eq(contentId))
                .fetchOne();

        return Optional.ofNullable(content);
    }
}
