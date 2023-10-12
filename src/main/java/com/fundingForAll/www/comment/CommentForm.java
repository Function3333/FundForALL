package com.fundingForAll.www.comment;

import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.user.User;
import lombok.Data;

@Data
public class CommentForm {
    private int id;
    private String userId;
    private User user;
    private int fundId;
    private Fund fund;
    private Integer parentCommentId;
    private String content;

}
