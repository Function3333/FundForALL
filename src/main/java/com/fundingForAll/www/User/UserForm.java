package com.fundingForAll.www.User;

import com.fundingForAll.www.Content.Image;
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
