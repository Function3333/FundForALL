package com.fundingForAll.www.user;

import com.fundingForAll.www.content.Image;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserForm {
    private String userId;
    private String password;
    private String email;
    private String bankName;
    private String bankAccount;
    private MultipartFile userImgMultipartFile;
    private Image userImg;
}
