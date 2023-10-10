package com.fundingForAll.www.Content;

import com.fundingForAll.www.Fund.Fund;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("M")
public class Music extends Content{

    @OneToOne
    @JoinColumn(name = "MUSIC_FUND_ID")
    private Fund fund;

    public Music(Fund fund) {
        this.fund = fund;
    }
}
