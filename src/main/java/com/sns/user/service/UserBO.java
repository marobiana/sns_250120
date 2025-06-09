package com.sns.user.service;

import com.sns.common.HashUtils;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserBO {
    private final UserRepository userRepository;

    // i: loginId
    // o: UserEntity(단건) or null
    public UserEntity getUserEntityByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId).orElse(null);
    }

    // i: 4개 파라미터
    // o: UserEntity => boolean(true:성공)
    public boolean addUser(String loginId, String password, String name, String email) {
        // md5 알고리즘 - hashing
        // aaaa -> 74b8733745420d4d33f80c4663dc5e5
        // aaaa -> 74b8733745420d4d33f80c4663dc5e5
        String hashedPassword = HashUtils.md5(password);

        // 실질적으로 오류 발생 시 null return 되지 않음. (try-catch로 처리해야함)
        UserEntity user = userRepository.save(
                UserEntity.builder()
                        .loginId(loginId)
                        .password(hashedPassword)
                        .name(name)
                        .email(email)
                        .build()
        );

        return user == null ? false : true;
    }

    // i: loginId, password
    // o: UserEntity(단건) or null
    public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
        // 비밀번호 해싱
        String hashedPassword = HashUtils.md5(password);

        // DB 조회
        return userRepository.findByLoginIdAndPassword(loginId, hashedPassword).orElse(null);
    }

    // i: userId (pk)
    // o: UserEntity or null
    public UserEntity getUserEntityById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
