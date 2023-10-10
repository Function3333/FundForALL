package com.fundingForAll.www.Content;

import com.fundingForAll.www.Fund.Fund;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("V")
public class Video extends Content{
    @ManyToOne
    @JoinColumn(name = "VIDEO_FUND_ID")
    private Fund fund;

    public Video(Fund fund) {
        this.fund = fund;

        if(this.fund.getVideoList() != null) {
            this.fund.getVideoList().remove(this);
        }
        this.fund.getVideoList().add(this);
    }
}
