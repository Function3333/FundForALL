package com.fundingForAll.www.fund;



import com.fundingForAll.www.content.QContent;
import com.fundingForAll.www.fund.QFund;
import com.fundingForAll.www.utils.Search;
import com.fundingForAll.www.utils.SearchType;
import com.fundingForAll.www.utils.SortType;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FundRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public FundRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(this.em);
    }

    public Fund save(Fund fund) {
        em.persist(fund);
        return fund;
    }

    public void delete(Fund fund) {
        em.remove(fund);
    }

    public Optional<Fund> findById(int fundNo) {
        return Optional.ofNullable(em.find(Fund.class, fundNo));
    }

    public List<Fund> getFundList(Search search) {
        QFund qFund = QFund.fund;
        QContent qContent = QContent.content;

        SearchType searchType = search.getSearchType();
        SortType sortType = search.getSortType();
        String searchKeyword = search.getSearchKeyword();

        List<Fund> fundList = queryFactory
                .selectFrom(qFund)
                .where(eqSearchType(searchType, searchKeyword))
                .orderBy(createOrderSpecifier(sortType))
                .offset(search.getStartRowNum())
                .limit(search.getEndRowNum())
                .fetch();

        return fundList;
    }

    public int getFundTotalCount() {
        QFund qFund = QFund.fund;

        Long totalCount = queryFactory
                .select(qFund.count())
                .from(qFund)
                .fetchOne();
        return totalCount.intValue();
    }

    //dynamic query
    private BooleanExpression eqSearchType(SearchType searchType, String searchKeyword) {
        QFund qFund = QFund.fund;

        return switch (searchType) {
            case ID -> qFund.user.id.like(searchKeyword);
            case TITLE -> qFund.title.contains(searchKeyword);
            case NONE -> null;
        };
    }

    private OrderSpecifier createOrderSpecifier(SortType sortType) {
        QFund qFund = QFund.fund;

        return switch (sortType) {
            case VIEWS -> new OrderSpecifier(Order.DESC, qFund.views);
            case REG_DATE -> new OrderSpecifier(Order.DESC, qFund.regDate);
            default -> new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);
        };
    }
}
