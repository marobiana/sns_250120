package com.sns.like;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LikeRestController {

    // GET /like?postId=2   @RequestParam("postId")
    // GET /like/2          @PathVariable(name = "postId")
    @GetMapping("/like/{postId}")
    public Map<String, Object> likeToggle(
            @PathVariable(name = "postId") int postId,
            HttpSession session
    ) {
        // 로그인 확인

        // toggle 로직 작성
        likeBO.toggle();

        // 응답값
    }
}






