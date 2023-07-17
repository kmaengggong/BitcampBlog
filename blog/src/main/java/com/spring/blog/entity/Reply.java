package com.spring.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;
import java.time.LocalDateTime;

//Entity는 불변성을 지키기 위해서 Setter를 제공하지 않는다.
// 한번 생성된 데이터가 변경될 가능성을 없앤다.
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@DynamicInsert
@Setter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;
    @Column(nullable = false)
    private long blogId;
    @Column(nullable = false)
    private String replyWriter;
    @Column(nullable = false)
    private String replyContent;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void setDate(){
        this.publishedAt = LocalDateTime.now();
//        this.publishedAt = LocalDateTime.parse("yyyy-mm-dd HH:MM:SS");
        this.updatedAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.parse("yyyy-mm-dd HH:MM:SS");
    }

    @PreUpdate
    public void setUpdateValue(){
        this.updatedAt = LocalDateTime.now();
    }
}