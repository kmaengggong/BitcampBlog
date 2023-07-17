package com.spring.blog.config;

import com.spring.blog.service.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Basic 방식 인증을 사용하도록 하는 설정 파일
@Configuration
public class BasicSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Autowired
    public BasicSecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    // 정적 파일이나 .jsp 파일 등 스프링 시큐리티가 기본적으로 적용되지 않을 영역 설정하기.
    @Bean
    public WebSecurityCustomizer configure(){
        return web -> web.ignoring()
                .requestMatchers("/static/**","")
                .dispatcherTypeMatchers(DispatcherType.FORWARD);
    }

    // http 요청에 대한 웹 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(authorizationConfig -> {
                    authorizationConfig
                            .requestMatchers("/login", "/signup", "/user")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLoginConfig -> {
                    formLoginConfig
                            .loginPage("/login")
                            .defaultSuccessUrl("/blog/list");
                })
                .logout(logoutConfig -> {
                    logoutConfig
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();
//                .authorizeRequests()  // 인증, 인가 설정 시작부에 사용하는 메서드
//                .requestMatchers("/login", "/signup", "/user")
//                .permitAll()  // 위 경로들은 인증 없이 접속 가능
//                .anyRequest()  // 위에 적힌 경로 외엔
//                .authenticated()  // 로그인 필요
//                .and()  // 다음 설정으로 넘어가기
//                .formLogin()  //  로그인 폼으로 로그인 제어
//                .loginPage("/login")  // 로그인 페이지로 지정할 주소
//                .defaultSuccessUrl("/blog/list")  // 로그인 후 넘어갈 주소
//                .and()
//                .logout()  // 로그아웃 시
//                .logoutSuccessUrl("/login")  // 로그아웃 성공시 넘어갈 주소
//                .invalidateHttpSession(true)  // 로그아웃 후 세션 제거
//                .and()
//                .csrf()  // csrf 공격 방지용 토큰
//                .disable()  // 을 쓰지 않겠음
//                .build();
    }

    // 위의 설정을 따라가는 인증은 어떤 서비스 클래스를 통해서 설정할지
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserService userService) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)  // userService에 기술된 내용을 토대로 로그인 처리
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 암호화 모듈 import
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
