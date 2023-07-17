package com.spring.blog.service;

import com.spring.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired  // Spring 4 부터 단일 멤버변수는 생성자 있으면 자동 주입됨
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override  // 회원 관련해서는 로그인 되는지 마는지 여부만 따짐
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
