package com.fundingForAll.www.repositoryTest;


import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.Fund.FundRepository;
import com.fundingForAll.www.utils.Search;
import com.fundingForAll.www.utils.SearchType;
import com.fundingForAll.www.utils.SortType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class FundRepositoryTest {

    @Autowired
    private FundRepository fundRepository;

    @Test
    void getTotalCount() {
        int totalCount = fundRepository.getFundTotalCount();

        Assertions.assertThat(totalCount).isEqualTo(20);
    }

    @Test
    @Transactional
    void getFundList() {
        Search search = new Search();
        search.setSearchKeyword("");
        search.setCurrentPage(2);
        search.setSearchType(SearchType.NONE);
        search.setSortType(SortType.VIEWS);

        List<Fund> fundList = fundRepository.getFundList(search);
        System.out.println("fundList size = " + fundList.size());

        for(Fund fund : fundList) {
            System.out.println(fund.toString());
        }
    }

    @Test
    @Transactional
    void getFund() {
        Fund fund = new Fund();
        fund.setTitle("test");
        fundRepository.save(fund);

        Fund findFund = fundRepository.findById(fund.getFundNo()).get();
        Assertions.assertThat(fund.getTitle()).isEqualTo(findFund.getTitle());

    }

}
