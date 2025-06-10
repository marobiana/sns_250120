package com.sns.like.service;

import org.springframework.stereotype.Service;

@Service
public class LikeBO {

    // i: userId, postId
    // o: X
    public void toggle(int userId, int postId) {
        // select
        // 있다 => 삭제
        // 없다 => 추가
    }
}
