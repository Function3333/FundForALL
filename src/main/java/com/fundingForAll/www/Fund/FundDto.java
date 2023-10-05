package com.fundingForAll.www.Fund;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FundDto {
    private String title;
    private String content;
    private int targetMoney;
    private int currentMoney;
    private int views;
    private List<MultipartFile> images;
    private List<MultipartFile> videos;
    private MultipartFile music;
}
