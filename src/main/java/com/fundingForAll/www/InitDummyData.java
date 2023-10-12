package com.fundingForAll.www;

import com.fundingForAll.www.comment.Comment;
import com.fundingForAll.www.comment.CommentRepository;
import com.fundingForAll.www.content.ContentRepository;
import com.fundingForAll.www.content.Image;
import com.fundingForAll.www.content.Music;
import com.fundingForAll.www.content.Video;
import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.fund.FundRepository;
import com.fundingForAll.www.user.User;
import com.fundingForAll.www.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitDummyData implements CommandLineRunner {

    private UserRepository userRepository;
    private FundRepository fundRepository;
    private ContentRepository contentRepository;
    private CommentRepository commentRepository;

    @Autowired
    public InitDummyData(UserRepository userRepository, FundRepository fundRepository, ContentRepository contentRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.contentRepository = contentRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initDummyData();
    }

    public void initDummyData() {
        for (int i = 0; i < 20; i++) {

            //init user
            User user = new User();
            user.setId("user"+i);
            user.setPassword("password" + i);
            user.setEmail("user" + i + "@example.com");
            user.setBankName("bankName" + i);
            user.setBankAccount("1234567890" + i);
            userRepository.save(user);

            //init userImg
            Image image = new Image(user);
            contentRepository.save(image);


            //init fund
            Fund fund = new Fund();
            fund.setTitle("fund" + i);
            fund.setContent("fund content " + i);
            fund.setTargetMoney(10000 + i);
            fund.setCurrentMoney(i);
            fund.setViews(i);
            fund.setUser(user);

            fundRepository.save(fund);

            //init comment
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setFund(fund);
            comment.setContent("comment content" + i);

            if(i != 0) comment.setParentCommentId(i);

            commentRepository.save(comment);

            //init content
            Image fundImg = new Image(fund);
            Video fundVideo = new Video(fund);
            Music fundMusic = new Music(fund);

            contentRepository.save(fundImg);
            contentRepository.save(fundVideo);
            contentRepository.save(fundMusic);
        }
    }
}
