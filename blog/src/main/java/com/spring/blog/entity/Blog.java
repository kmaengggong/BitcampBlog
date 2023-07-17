package com.spring.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import java.time.LocalDateTime;

// 역직렬화(DB -> 자바객체) 가 가능하도록 blog 테이블 구조에 맞게 멤버변수 선언
// 패러다임 불일치 해결
@Getter
@Setter   // 데이터 불변성을 위해 엔터티에는 setter를 작성하지 않는다.
// 생성자를 통해 처음 값이 들어간 후 변경이 없어야 한다.
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder   //빌더패턴 생성자(전체 생성자가 있어야 된다)
@Entity
//@DynamicInsert  // null인 필드값에 기본값 지정된 요소를 집어넣어줌.
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long blogId;
    @Column(nullable = false)   //nullable = false => not null
    private String writer;
    @Column(nullable = false)
    private String blogTitle;
    @Column(nullable = false)
    private String blogContent;

    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
    @ColumnDefault("0")
    private Long blogCount;

    @PrePersist // insert 되기 전 실핼할 로직을 작성
    public void setBlogCnt(){
//        this.blogCount = this.blogCount == null ? 0 : this.blogCount;
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate //update 되기 전 실행할 로직 작성
    public void setUpdateValue(){
        this.updatedAt = LocalDateTime.now();
    }
}