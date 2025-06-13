package com.sns.post.service;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostBO {
    private final PostRepository postRepository;
    private final FileManagerService fileManager;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByIdDesc();
    }

    // input: 파라미터들 output:PostEntity
    public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {

        // 업로드 후 imagePath를 받아옴
        String imagePath = fileManager.uploadFile(file, userLoginId);
        if (imagePath == null) {
            return null;
        }

        return postRepository.save(
                PostEntity.builder()
                        .userId(userId)
                        .content(content)
                        .imagePath(imagePath)
                        .build());
    }

    // 글삭제
    // i:postId
    // o:
    @Transactional // DB 수행 시 하나라도 실패하면 자동 복구 (격리 수준)
    public void deletePostByPostId() {
        // 글삭제
        // 이미지 삭제
        // 댓글 삭제
        // 좋아요 삭제
    }
}
