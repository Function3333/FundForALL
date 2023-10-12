package com.fundingForAll.www.comment;

import com.fundingForAll.www.fund.Fund;
import com.fundingForAll.www.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUND_ID")
    private Fund fund;

    @Transient
    private List<Comment> childCommentList = new ArrayList<>();

    @Column(name = "PARENT_COMMENT_ID")
    private Integer parentCommentId;

    @Column(name = "COMMENT_CONTENT")
    private String content;

    @CreationTimestamp
    @Column(name = "COMMENT_REG_DATE")
    private Timestamp regDate;

    public Comment formToEntity(CommentForm commentForm) {
        this.user = commentForm.getUser();
        this.fund = commentForm.getFund();
        this.parentCommentId = getParentCommentId();
        this.content = commentForm.getContent();

        return this;
    }

    public void updateComment(CommentForm commentForm) {
        this.content = commentForm.getContent();
    }
}
