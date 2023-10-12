package com.fundingForAll.www.donate;

import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Donate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DONATE_ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUND_ID")
    private Fund fund;

    @Column(name = "DONATE_MONEY")
    private int donateMoney;

    @CreationTimestamp
    @Column(name = "DONATE_DATE")
    private Timestamp donateDate;

    public Donate formToEntity(DonateForm donateForm) {
        this.user = donateForm.getUser();
        this.fund = donateForm.getFund();
        this.donateMoney = donateForm.getDonateMoney();

        return this;
    }
}
