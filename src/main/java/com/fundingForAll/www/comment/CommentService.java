package com.fundingForAll.www.comment;

import com.fundingForAll.www.utils.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment save(CommentForm commentForm) {
        Comment comment = new Comment().formToEntity(commentForm);
        return comment;
    }

    @Transactional
    public Comment update(int commentId , CommentForm commentForm) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if(findComment.isPresent()) {
            Comment comment = findComment.get();
            comment.updateComment(commentForm);

            return comment;
        }

        return null;
    }

    public Comment findByCommentId(int commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        return (findComment.isPresent()) ? findComment.get() : null;
    }

    public List<Comment> findByFundId(int fundId, SortType sortType) {
        List<Comment> commentList = commentRepository.findByFundId(fundId, sortType);

        commentList.stream()
                .forEach(comment -> {
                    List<Comment> childCommentList = commentRepository.findByParentCommentId(comment.getId());
                    childCommentList.stream().forEach(childComment -> {
                        comment.getChildCommentList().add(childComment);
                    });
                });

        return commentList;
    }


}
