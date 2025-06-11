package com.sns.like.service;

import com.sns.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeBO {
    private final LikeMapper likeMapper;

    // i: userId, postId
    // o: X
    public void toggle(int userId, int postId) {
        // select
        if (likeMapper.selectLikeCountByUserIdPostId(userId, postId) > 0) {
            // 있다 => 삭제
            likeMapper.deleteLikeByUserIdPostId(userId, postId);
        } else {
            // 없다 => 추가
            likeMapper.insertLike(userId, postId);
        }
    }

    // i: postId
    // o: 좋아요 개수
    public int getLikeCountByPostId(int postId) {
        return likeMapper.selectLikeCountByPostId(postId);
    }
}
