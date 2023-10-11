package com.fundingForAll.www.Fund;

import com.fundingForAll.www.Content.Image;
import com.fundingForAll.www.Content.Music;
import com.fundingForAll.www.Content.Video;
import com.fundingForAll.www.User.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class FundForm {
    private String title;
    private String content;
    private int targetMoney;
    private String userId;
    private User user;
    private List<MultipartFile> imageMultipartFileList = new ArrayList<>();
    private List<MultipartFile> videoMultipartFileList = new ArrayList<>();
    private MultipartFile musicMultipartFile;
    private List<Image> imageList = new ArrayList<>();
    private List<Video> videoList = new ArrayList<>();
    private Music music;
}
