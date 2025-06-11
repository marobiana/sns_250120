package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    // 카운트 쿼리 합침(옵션)
    public int selectLikeCountByPostIdOrUserId(
            @Param("postId") int postId,
            @Param("userId") Integer userId
    );

    // i: userId, postId
    // o: 좋아요 개수(int)
//    public int selectLikeCountByUserIdPostId(
//            @Param("userId") int userId,
//            @Param("postId") int postId
//    );

    //public int selectLikeCountByPostId(int postId);

    public void deleteLikeByUserIdPostId(
            @Param("userId") int userId,
            @Param("postId") int postId
    );

    public void insertLike(
            @Param("userId") int userId,
            @Param("postId") int postId
    );
}
