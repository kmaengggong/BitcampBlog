package com.spring.blog.dto;

import com.spring.blog.entity.Blog;
import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class BlogUpdateDTO {
    private long blogId;
    private String writer;
    private String blogTitle;
    private String blogContent;

    // entity -> DTO o
    // DTO -> entity x
    // DTO가 entity의 하위개념. entity에서 사용할 것만 가져옴
    // Data Transfer Object
//    public BlogUpdateDTO(Blog blog){
//        this.blogId = blog.getBlogId();
//        this.author = blog.getAuthor();
//        this.blogTitle = blog.getBlogTitle();
//        this.blogContent = blog.getBlogContent();
//    }
}
