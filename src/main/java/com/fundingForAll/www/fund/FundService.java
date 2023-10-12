package com.fundingForAll.www.fund;

import com.fundingForAll.www.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FundService {

    private FundRepository fundRepository;

    @Autowired
    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Transactional
    public Fund save(FundForm fundForm) {
        Fund fund = new Fund().formToEntity(fundForm);
        fundRepository.save(fund);

        return fund;
    }

    @Transactional
    public Fund updateFund(int fundId, FundForm fundForm) {
        Optional<Fund> findFund = fundRepository.findById(fundId);

        if(findFund.isPresent()) {
            Fund fund = findFund.get();
            fund.updateFund(fundForm);

            return fund;
        }
        return null;
    }

    @Transactional
    public void increaseFundView(int fundId) {
        Optional<Fund> findFund = fundRepository.findById(fundId);

        if(findFund.isPresent()) {
            Fund fund = findFund.get();
            fund.increaseView();
        }
    }

    @Transactional
    public Fund increaseFundCurrentMoney(int fundId, int inputMoney) {
        Optional<Fund> findFund = fundRepository.findById(fundId);

        if(findFund.isPresent()) {
            Fund fund = findFund.get();
            fund.increaseCurrentMoney(inputMoney);

            return fund;
        }
        return null;
    }

    public Fund getFund(int fundId) {
        Optional<Fund> fund = fundRepository.findById(fundId);
        return (fund.isPresent()) ? fund.get() : null;
    }

    public List<Fund> searchFund(Search search) {
        List<Fund> fundList = fundRepository.getFundList(search);

        return fundList;
    }

    public int getFundTotalCount() {
        return fundRepository.getFundTotalCount();
    }
}
