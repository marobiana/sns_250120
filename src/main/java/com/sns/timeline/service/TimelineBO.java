package com.sns.timeline.service;

import com.sns.comment.domain.CommentDTO;
import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineBO {

    private final PostBO postBO;
    private final UserBO userBO;

    // i: X
    // o: List<CardDTO>
    public List<CardDTO> generateCardList() {
        List<CardDTO> cardList = new ArrayList<>();

        // 글 목록을 가져온다. List<PostEntity>
        List<PostEntity> postList = postBO.getPostList();
        
        // 반복문 돌린다.   PostEntity -> CardDTO => 리스트에 넣는다
        for (PostEntity postEntity : postList) {
            // 하나의 카드 = 글1개와 매핑
            CardDTO card = new CardDTO();
            // 글
            card.setPost(postEntity);
            // 글쓴이
            UserEntity user = userBO.getUserEntityById(postEntity.getUserId());
            card.setUser(user);
            
            // 댓글 N개
            List<CommentDTO> commentList = commentBO.generateCommentListByPostId(글번호);
            card.setCommentList(commentList);

            // !!!!!!!!! 마지막 리스트에 꼭 담기! !!!!!
            cardList.add(card);
        }

        return cardList;
    }
}
