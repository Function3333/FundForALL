package com.fundingForAll.www.Fund;

import com.fundingForAll.www.Content.Content;
import com.fundingForAll.www.Donate.Donate;
import com.fundingForAll.www.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter @Setter
public class Fund {

    @Id @Column(name = "FUND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fundNo;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FUND_REG_DATE")
    private Timestamp regDate;

    @Column(name = "FUND_TITLE")
    private String title;

    @Column(name = "FUND_CONTENT")
    private String content;

    @Column(name = "FUND_TARGET_MONEY")
    private int targetMoney;

    @Column(name = "FUND_VIEWS")
    private int views;

    @Column(name = "FUND_RECOMMEND")
    private int recommend;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "fund")
    private List<Donate> donateList;

    @OneToMany(mappedBy = "fund")
    private List<Content> imgList;

    @OneToMany(mappedBy = "fund")
    private List<Content> videoList;

    @OneToOne(mappedBy = "fund")
    private Content music;
}
