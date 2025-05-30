package com.sns.comment.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentBO {
    private final CommentMapper commentMapper;

    public int addComment(int postId, int userId, String content) {
        return commentMapper.insertComment(postId, userId, content);
    }

    public List<Comment> getCommentList() {
        return commentMapper.selectCommentList();
    }
}
