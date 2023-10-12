package com.fundingForAll.www.content;

import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("I")
public class Image extends Content{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_FUND_ID")
    private Fund fund;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_USER_ID")
    private User user;

    public Image(Fund fund) {
        this.fund = fund;

        if(this.fund.getImgList() != null) {
            this.fund.getImgList().remove(this);
        }
        this.fund.getImgList().add(this);
    }

    public Image(User user) {
        this.user = user;
    }
}
