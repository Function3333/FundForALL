package com.fundingForAll.www.Donate;

import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Donate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DONATE_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "DONATE_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DONATE_FUND_ID")
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
