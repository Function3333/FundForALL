package com.fundingForAll.www.comment;

import com.fundingForAll.www.comment.QComment;
import com.fundingForAll.www.utils.SortType;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository {

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public CommentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public Optional<Comment> findById(int commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    public List<Comment> findByParentCommentId(int parentCommentId) {
        QComment qComment = QComment.comment;

        List<Comment> childCommentList = queryFactory
                .selectFrom(qComment)
                .where(qComment.parentCommentId.eq(parentCommentId))
                .fetch();

        return childCommentList;
    }

    public List<Comment> findByFundId(int fundId, SortType sortType) {
        QComment qComment = QComment.comment;

        List<Comment> commentList = queryFactory
                .selectFrom(qComment)
                .where(qComment.fund.fundNo.eq(fundId))
                .orderBy(createOrderSpecifier(sortType))
                .fetch();

        return commentList;
    }

    private OrderSpecifier createOrderSpecifier(SortType sortType) {
        QComment qComment = QComment.comment;

        return switch (sortType) {
            case REG_DATE -> new OrderSpecifier(Order.DESC, qComment.regDate);
            default -> new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);
        };
    }
}
