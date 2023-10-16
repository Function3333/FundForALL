package com.fundingForAll.www.content;

import com.fundingForAll.www.content.QContent;
import com.fundingForAll.www.content.QImage;
import com.fundingForAll.www.content.QMusic;
import com.fundingForAll.www.content.QVideo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public ContentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public String save(Content content) {
        em.persist(content);
        return content.getId();
    }

    public void deleteById(String contentId) {
        Optional<Content> findContent = findById(contentId);

        if(findContent.isPresent()) {
            Content content = findContent.get();
            em.remove(content);
        }
    }

    public Optional<Content> findById(String contentId) {
        QContent qContent = QContent.content;

        Content content = queryFactory.
                selectFrom(qContent)
                .where(qContent.id.eq(contentId))
                .fetchOne();

        return Optional.ofNullable(content);
    }

    public Optional<String> findImageByUserId(String userId) {
        QImage qImage = QImage.image;

        String imgId = queryFactory
                .select(qImage.id)
                .from(qImage)
                .where(qImage.user.id.eq(userId))
                .fetchOne();

        return Optional.ofNullable(imgId);
    }

    public List<String> findImageByFundId(int fundId) {
        QImage qImage = QImage.image;

        List<String> imageList = queryFactory
                .select(qImage.id)
                .from(qImage)
                .where(qImage.fund.fundNo.eq(fundId))
                .fetch();

        return imageList;
    }

    public List<String> findVideoByFundId(int fundId) {
        QVideo qVideo = QVideo.video;

        List<String> videoList = queryFactory
                .select(qVideo.id)
                .from(qVideo)
                .where(qVideo.fund.fundNo.eq(fundId))
                .fetch();

        return videoList;
    }

    public String findMusicByFundId(int fundId) {
        QMusic qMusic = QMusic.music;

        String music = queryFactory
                .select(qMusic.id)
                .from(qMusic)
                .where(qMusic.fund.fundNo.eq(fundId))
                .fetchOne();

        return music;
    }
}
