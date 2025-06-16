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
        if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
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
        //return likeMapper.selectLikeCountByPostId(postId);
        return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
    }

    // i: userId(비로그인도 있음), postId
    // o: boolean (채울지 말지 여부 true:채운다 )
    public boolean isFilledLike(Integer userId, int postId) {
        // 1) 비로그인  => false
        if (userId == null) {
            return false;
        }
        // 2) 로그인 & 눌렀다  => true
        // 3) 로그인 & 안 눌렀다 => false
        //return likeMapper.selectLikeCountByUserIdPostId(userId, postId) > 0;
        return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
    }

    public void deleteLikesByPostId(int postId) {
        likeMapper.deleteLikesByPostId(postId);
    }
}
