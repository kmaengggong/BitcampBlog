package com.spring.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;  // null check 대비

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickName;

    // 비밀번호는 null 허용(OAuth2.0을 활용한 소셜 로그인은 비밀번호가 없음)
    private String password;

    @Builder                                    // 인증정보
    public User(String email, String password, String nickName, String auth){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    // UserDtails Override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    // 로그인에 사용할 아이디 입력(unique = true)
    @Override
    public String getUsername() {
        return email;
    }

    // 로그인 중이면 true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 밴 당하지 않았다면 true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
