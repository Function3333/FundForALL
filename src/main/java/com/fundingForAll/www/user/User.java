package com.fundingForAll.www.user;


import com.fundingForAll.www.content.Image;
import com.fundingForAll.www.donate.Donate;
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

    public User formToEntity(UserForm userForm) {
        this.id = userForm.getUserId();
        this.password = userForm.getPassword();
        this.email = userForm.getEmail();
        this.bankName = userForm.getBankName();
        this.bankAccount = userForm.getBankAccount();
        this.userImg = userForm.getUserImg();

        return this;
    }

    public void updateUser(UserForm userForm) {
        this.password = userForm.getPassword();
        this.email = userForm.getEmail();
        this.bankName = userForm.getBankName();
        this.bankAccount = userForm.getBankAccount();
        this.userImg = userForm.getUserImg();
    }
}
