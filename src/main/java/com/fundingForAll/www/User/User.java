package com.fundingForAll.www.User;


import com.fundingForAll.www.Content.Content;
import com.fundingForAll.www.Donate.Donate;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PHONE")
    private String phone;

    @Column(name = "USER_ACCOUNT")
    private String account;

    @OneToMany(mappedBy = "user")
    private List<Donate> donateList;

    @OneToOne
    @JoinColumn(name = "USER_CONTENT_ID")
    private Content content;
}
