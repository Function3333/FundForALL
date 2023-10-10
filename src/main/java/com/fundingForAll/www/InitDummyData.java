package com.fundingForAll.www;

import com.fundingForAll.www.Content.ContentRepository;
import com.fundingForAll.www.Content.Image;
import com.fundingForAll.www.Content.Music;
import com.fundingForAll.www.Content.Video;
import com.fundingForAll.www.Fund.Fund;
import com.fundingForAll.www.Fund.FundRepository;
import com.fundingForAll.www.User.User;
import com.fundingForAll.www.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDummyData implements CommandLineRunner {

    private UserRepository userRepository;
    private FundRepository fundRepository;
    private ContentRepository contentRepository;

    @Autowired
    public InitDummyData(UserRepository userRepository, FundRepository fundRepository, ContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        initDummyData();
    }

    public void initDummyData() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId("user"+i);
            user.setPassword("password" + i);
            user.setEmail("user" + i + "@example.com");
            user.setBankName("bankName" + i);
            user.setBankAccount("1234567890" + i);
            userRepository.save(user);

            Image image = new Image(user);
            contentRepository.save(image);


            Fund fund = new Fund();
            fund.setTitle("fund" + i);
            fund.setContent("fund content " + i);
            fund.setTargetMoney(10000 + i);
            fund.setCurrentMoney(i);
            fund.setViews(i);
            fund.setUser(user);

            fundRepository.save(fund);

            Image fundImg = new Image(fund);
            Video fundVideo = new Video(fund);
            Music fundMusic = new Music(fund);



            contentRepository.save(fundImg);
            contentRepository.save(fundVideo);
            contentRepository.save(fundMusic);
        }
    }
}
