package com.fundingForAll.www.Donate;

import com.fundingForAll.www.utils.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DonateService {

    private DonateRepository donateRepository;

    @Autowired
    public DonateService(DonateRepository donateRepository) {
        this.donateRepository = donateRepository;
    }

    @Transactional
    public Donate save(DonateForm donateForm) {
        Donate donate = new Donate().formToEntity(donateForm);
        donateRepository.save(donate);

        return donate;
    }

    public Donate findById(int donateId) {
        Optional<Donate> findDonate = donateRepository.findById(donateId);
        return (findDonate.isPresent()) ? findDonate.get() : null;
    }

    public List<Donate> findByUserId(String userId, SortType sortType) {
        return donateRepository.findByUserId(userId, sortType);
    }

    public List<Donate> findByFundId(int fundId, SortType sortType) {
        return donateRepository.findByFundId(fundId, sortType);
    }

}
