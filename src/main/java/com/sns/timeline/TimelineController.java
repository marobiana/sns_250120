package com.sns.timeline;

import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TimelineController {
    private final PostBO postBO;

    @GetMapping("/timeline")
    public String timeline(Model model) {
        // db select
        List<PostEntity> postList = postBO.getPostList();

        // model
        model.addAttribute("postList", postList);

        return "timeline/timeline";
    }
}
