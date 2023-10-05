package com.fundingForAll.www.User;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String password;
    private String email;
    private String bankName;
    private String bankAccount;
}
