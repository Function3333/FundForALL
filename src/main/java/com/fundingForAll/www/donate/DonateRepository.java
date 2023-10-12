package com.fundingForAll.www.donate;

import com.fundingForAll.www.donate.QDonate;
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
public class DonateRepository {

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public DonateRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Donate save(Donate donate) {
        em.persist(donate);
        return donate;
    }

    public Optional<Donate> findById(int donateId) {
        return Optional.ofNullable(em.find(Donate.class, donateId));
    }

    public List<Donate> findByUserId(String userId, SortType sortType) {
        QDonate qDonate = QDonate.donate;

        List<Donate> donateList = queryFactory
                .selectFrom(qDonate)
                .where(qDonate.user.id.eq(userId))
                .orderBy(createOrderSpecifier(sortType))
                .fetch();

        return donateList;
    }

    public List<Donate> findByFundId(int fundId, SortType sortType) {
        QDonate qDonate = QDonate.donate;

        List<Donate> donateList = queryFactory
                .selectFrom(qDonate)
                .where(qDonate.fund.fundNo.eq(fundId))
                .orderBy(createOrderSpecifier(sortType))
                .fetch();

        return donateList;
    }

    public int getDonateTotalCount() {
        QDonate qDonate = QDonate.donate;

        Long totalCount = queryFactory
                .select(qDonate.count())
                .from(qDonate)
                .fetchOne();
        return totalCount.intValue();
    }

    //dynamic query
    private OrderSpecifier createOrderSpecifier(SortType sortType) {
        QDonate qDonate = QDonate.donate;

        return switch (sortType) {
            case REG_DATE -> new OrderSpecifier(Order.DESC, qDonate.donateDate);
            case MONEY -> new OrderSpecifier(Order.ASC, qDonate.donateMoney);
            default -> new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);
        };
    }
}
