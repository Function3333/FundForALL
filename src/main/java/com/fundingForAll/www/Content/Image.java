package com.fundingForAll.www.Content;

import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("I")
public class Image extends Content{

    @ManyToOne
    @JoinColumn(name = "IMAGE_FUND_ID")
    private Fund fund;

    @OneToOne
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
