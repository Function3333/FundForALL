package com.fundingForAll.www.Fund;

import com.fundingForAll.www.Content.Content;
import com.fundingForAll.www.Content.Image;
import com.fundingForAll.www.Content.Music;
import com.fundingForAll.www.Content.Video;
import com.fundingForAll.www.Donate.Donate;
import com.fundingForAll.www.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter @Setter
public class Fund {

    @Id @Column(name = "FUND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fundNo;

    @CreationTimestamp
    @Column(name = "FUND_REG_DATE")
    private Timestamp regDate;

    @Column(name = "FUND_TITLE")
    private String title;

    @Column(name = "FUND_CONTENT")
    private String content;

    @Column(name = "FUND_TARGET_MONEY")
    private int targetMoney;

    @Column(name = "FUND_CURRENT_MONEY")
    private int currentMoney;

    @Column(name = "FUND_VIEWS")
    private int views;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "fund")
    private List<Donate> donateList = new ArrayList<>();

    @OneToMany(mappedBy = "fund")
    private List<Image> imgList = new ArrayList<>();

    @OneToMany(mappedBy = "fund")
    private List<Video> videoList = new ArrayList<>();

    @OneToOne(mappedBy = "fund")
    private Music music;

    public Fund formToEntity(FundForm fundForm) {
        this.title = fundForm.getTitle();
        this.content = fundForm.getContent();
        this.targetMoney = fundForm.getTargetMoney();
        this.user = fundForm.getUser();
        this.imgList = fundForm.getImageList();
        this.videoList = fundForm.getVideoList();
        this.music = fundForm.getMusic();

        return this;
    }

    public void updateFund(FundForm fundForm) {
        this.title = fundForm.getTitle();
        this.content = fundForm.getContent();
        this.imgList = fundForm.getImageList();
        this.videoList = fundForm.getVideoList();
        this.music = fundForm.getMusic();
    }

    public void increaseView() {
        this.views++;
    }

    public void increaseCurrentMoney(int inputMoney) {
        this.currentMoney += inputMoney;
    }
}
