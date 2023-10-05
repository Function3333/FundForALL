package com.fundingForAll.www.Fund;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class FundRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public FundRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(this.em);
    }

    @Transactional
    public int save(Fund fund) {
        em.persist(fund);

        return fund.getFundNo();
    }

    @Transactional
    public void delete(Fund fund) {
        em.remove(fund);
    }

    public Optional<Fund> findByNo(int fundNo) {
        Optional<Fund> findFund = Optional.ofNullable(em.find(Fund.class, fundNo));
        return findFund;
    }

    /*
    * 검색조건 fundTitle, userId
    * 정렬조건
    * 최근페이지
    * */


}
