package com.fundingForAll.www.User;


import com.fundingForAll.www.Content.Content;
import com.fundingForAll.www.Donate.Donate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
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

    public User createUser(UserDto userDto) {
        this.id = userDto.getId();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.account = userDto.getAccount();

        return this;
    }

    public User updateUser(UserDto userDto) {
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.account = userDto.getAccount();

        return this;
    }
}
