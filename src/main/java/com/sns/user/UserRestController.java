package com.sns.user;

import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
    private final UserBO userBO;

    /**
     * 로그인 아이디 중복확인 API
     * @param loginId
     * @return
     */
    @GetMapping("/is-duplicate-id")
    public Map<String, Object> isDuplicateId(
            @RequestParam("loginId") String loginId
    ) {

        // db 조회
        UserEntity user = userBO.getUserEntityByLoginId(loginId);
        boolean isDuplicate = false;
        if (user != null) {
            isDuplicate = true;
        }

        // 응답값
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("is_duplicate_id", isDuplicate);
        return result;
    }

    /**
     * 회원가입 API
     * @param loginId
     * @param password
     * @param name
     * @param email
     * @return
     */
    @PostMapping("/sign-up")
    public Map<String, Object> signUp(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        // DB Insert
        boolean isSuccess = userBO.addUser(loginId, password, name, email);

        // 응답
        Map<String, Object> result = new HashMap<>();
        if (isSuccess) {
            result.put("code", 200);
            result.put("result", "성공");
        } else {
            result.put("code", 500);
            result.put("error_message", "회원가입에 실패했습니다.");
        }
        return result;
    }
}
