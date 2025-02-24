package org.test.memberlogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.memberlogin.dto.UserSignupRequest;
import org.test.memberlogin.entity.Role;
import org.test.memberlogin.entity.User;
import org.test.memberlogin.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserSignupRequest request) {
            // 중복 검사
            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            // 유저 생성 후 저장
            User user = new User(request.getUsername(), encodedPassword, request.getEmail(), Role.USER);
            userRepository.save(user);
        }
}
