package com.fundingForAll.www.User;


import com.fundingForAll.www.Content.Content;
import com.fundingForAll.www.Content.Image;
import com.fundingForAll.www.Donate.Donate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "USER_BANK_NAME")
    private String bankName;
    
    @Column(name = "USER_BANK_bankAccount")
    private String bankAccount;

    @OneToMany(mappedBy = "user")
    private List<Donate> donateList;

    @OneToOne(mappedBy = "user")
    private Image userImg;

    public User createUser(UserDto userDto) {
        this.id = userDto.getId();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.bankAccount = userDto.getBankAccount();
        return this;
    }

    public User updateUser(UserDto userDto) {
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.bankAccount = userDto.getBankAccount();

        return this;
    }
}
