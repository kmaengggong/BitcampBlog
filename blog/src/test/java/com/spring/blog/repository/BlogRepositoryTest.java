//package com.spring.blog.repository;
//
//import com.spring.blog.entity.Blog;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class BlogRepositoryTest {
//    @Autowired
//    BlogRepository blogRepository;
//
//    @BeforeEach
//    public void setBlogTable(){
//        blogRepository.createBlogTable();
//        blogRepository.insertTestData();
//    }
//
//    @Test
//    @DisplayName("전체 글 3개를 가져옴")
//    public void findAllTest(){
//        // Given
//        int idx = 2;  // 3번째 리스트
//
//        // When
//        List<Blog> blogList = blogRepository.findAll();
//
//        // Then
//        Assertions.assertEquals(3, blogList.size());
//        Assertions.assertEquals(3, blogList.get(idx).getBlogId());
//    }
//
//    @Test
//    @DisplayName("1번 글 가져옴")
//    public void findByIdTest(){
//        // Given
//        long blogId = 1;
//
//        // When
//        Blog blog = blogRepository.findById(blogId);
//
//        // Then
//        Assertions.assertEquals(blogId, blog.getBlogId());
//    }
//
//    @Test
//    @DisplayName("4번 글 저장")
//    public void saveTest() {
//        // Given
//        //Blog blog = new Blog();
//        //blog.setAuthor("4번유저");
//        //blog.setBlogTitle("4번제목");
//        //blog.setBlogContent("4번내용");
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
//        blogRepository.save(blog);
//
//        // Then
//        Assertions.assertEquals(4, blogRepository.findAll().size());
//        Assertions.assertEquals(author, blogRepository.findById(4).getAuthor());
//        Assertions.assertEquals(blogTitle, blogRepository.findById(4).getBlogTitle());
//        Assertions.assertEquals(blogContent, blogRepository.findById(4).getBlogContent());
//    }
//
//    @Test
//    @DisplayName("1번 글 삭제")
//    public void deleteByIdTest(){
//        // Given
//        long blogId = 1;
//
//        // When
//        blogRepository.deleteById(blogId);
//
//        // Then
//        Assertions.assertEquals(2, blogRepository.findAll().size());
//        Assertions.assertNull(blogRepository.findById(blogId));
//    }
//
//    @Test
//    @DisplayName("1번 글 수정")
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
//        blogRepository.update(blog);
//
//        // Then
//        Assertions.assertEquals(blogTitle, blogRepository.findById(blogId).getBlogTitle());
//        Assertions.assertEquals(blogContent, blogRepository.findById(blogId).getBlogContent());
//    }
//
//    @AfterEach
//    public void dropBlogTable(){
//        blogRepository.dropBlogTable();
//    }
//}
