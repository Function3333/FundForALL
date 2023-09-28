package com.fundingForAll.www.Content;

import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CONTENT_ID")
    private UUID id;

    @OneToOne(mappedBy = "content")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CONTENT_FUND_ID")
    private Fund fund;
}
