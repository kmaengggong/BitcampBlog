//package com.spring.blog.service;
//
//import com.spring.blog.entity.Blog;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//public class BlogServiceTest {
//    @Autowired
//    BlogService blogService;
//
//    @Test
//    @Transactional
//    public void findAllTest() {
//        // Given
//        // When
//        // Then
//        Assertions.assertEquals(3, blogService.findAll(1l).getSize());
//    }
//
//    @Test
//    @Transactional
//    public void findByIdTest(){
//        // Given
//        long blogId = 1;
//        // When
//        // Then
//        Assertions.assertEquals(blogId, blogService.findById(blogId).getBlogId());
//    }
//
//    @Test
//    @Transactional
//    public void deleteByIdTest(){
//        // Given
//        long blogId = 1;
//
//        // When
//        blogService.deleteById(blogId);
//
//        // Then
//        Assertions.assertEquals(2, blogService.findAll(1l).getSize());
//        Assertions.assertNull(blogService.findById(blogId));
//    }
//
//    @Test
//    @Transactional
//    public void saveTest(){
//        // Given
//        int lastIdx = 3;
//        String author = "4번유저";
//        String blogTitle = "4번제목";
//        String blogContent = "4번내용";
//
//        Blog blog = Blog.builder()
//                .author(author)
//                .blogTitle(blogTitle)
//                .blogContent(blogContent)
//                .build();
//
//        // When
//        blogService.save(blog);
//        Page<Blog> blogList = blogService.findAll(1l);
//
//        // Then
//        Assertions.assertEquals(4, blogService.findAll(1l).getSize());
//        Assertions.assertEquals(author, blogList.get(lastIdx).getAuthor());
//        Assertions.assertEquals(blogTitle, blogList.get(lastIdx).getBlogTitle());
//        Assertions.assertEquals(blogContent, blogList.get(lastIdx).getBlogContent());
//    }
//
//    @Test
//    @Transactional
//    public void updateTest(){
//        // Given
//        long blogId = 1;
//        String blogTitle = "수정된1번제목";
//        String blogContent = "수정된1번내용";
//
//        Blog blog = Blog.builder()
//                .blogId(blogId)
//                .blogTitle(blogTitle)
//                .blogContent(blogContent)
//                .build();
//
//        // When
//        blogService.update(blog);
//
//        // Then
//        Assertions.assertEquals(blogTitle, blogService.findById(blogId).getBlogTitle());
//        Assertions.assertEquals(blogContent, blogService.findById(blogId).getBlogContent());
//    }
//}