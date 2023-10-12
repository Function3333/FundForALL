package com.fundingForAll.www.serviceTest;

import com.fundingForAll.www.comment.Comment;
import com.fundingForAll.www.comment.CommentService;
import com.fundingForAll.www.utils.SortType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @Transactional
    void findByFundId() {
        List<Comment> commentList = commentService.findByFundId(1, SortType.NONE);

        commentList.stream().forEach(comment -> {
            Assertions.assertThat(comment.getChildCommentList().get(0).getId()).isEqualTo(2);
        });

        Assertions.assertThat(commentList.size()).isEqualTo(1);
    }
}
