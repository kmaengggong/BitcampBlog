//package com.spring.blog.repository;
//
//import com.spring.blog.dto.ReplyFindDTO;
//import com.spring.blog.dto.ReplyInsertDTO;
//import com.spring.blog.dto.ReplyUpdateDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//public class ReplyRepositoryTest {
//    @Autowired
//    ReplyRepository replyRepository;
//
//    @Test
//    @Transactional
//    @DisplayName("2번 글의 모든 댓글을 가져옴")
//    public void findAllBlyBlogIdTest(){
//        // Given
//        long blogId = 2;
//
//        // When
//        List<ReplyFindDTO> replyList = replyRepository.findAllByBlogId(blogId);
//
//        // Then
//        Assertions.assertEquals(3, replyList.size());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("3번 댓글을 가져옴")
//    public void findByReplyIdTest(){
//        // Given
//        long replyId = 3;
//
//        // When
//        ReplyFindDTO reply = replyRepository.findByReplyId(replyId);
//
//        // Then
//        Assertions.assertEquals(3, reply.getReplyId());
//        Assertions.assertEquals("3번유저", reply.getReplyAuthor());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("2번 댓글 삭제")
//    public void deleteByReplyIdTest(){
//        // Given
//        long blogId = 2;
//        long replyId = 2;
//
//        // When
//        replyRepository.deleteByReplyId(replyId);
//
//        // Then
//        Assertions.assertEquals(2, replyRepository.findAllByBlogId(blogId).size());
//        Assertions.assertNull(replyRepository.findByReplyId(replyId));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("2번 글의 댓글 전부 삭제")
//    public void deleteByBlogIdTest(){
//        // Given
//        long blogId = 2;
//
//        // When
//        replyRepository.deleteByBlogId(blogId);
//
//        // Then
//        Assertions.assertEquals(0, replyRepository.findAllByBlogId(blogId).size());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("4번 댓글 추가")
//    public void saveTest(){
//        // Given
//        long blogId = 1;
//        String replyAuthor = "1번유저";
//        String replyContent = "추가된 댓글입니다";
//        ReplyInsertDTO replyInsertDTO = ReplyInsertDTO.builder()
//                .blogId(blogId)
//                .replyAuthor(replyAuthor)
//                .replyContent(replyContent)
//                .build();
//
//        // When
//        replyRepository.save(replyInsertDTO);
//        List<ReplyFindDTO> replyFindDTOList = replyRepository.findAllByBlogId(blogId);
//        ReplyFindDTO reply = replyFindDTOList.get(replyFindDTOList.size()-1);
//
//        // Then
//        Assertions.assertEquals(1, replyFindDTOList.size());
//        Assertions.assertEquals(replyAuthor, reply.getReplyAuthor());
//        Assertions.assertEquals(replyContent, reply.getReplyContent());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("1번 댓글 수정")
//    public void updateTest(){
//        // Given
//        long replyId = 3;
//        String replyAuthor = "2번유저";
//        String replyContent = "수정된 댓글입니다";
//        ReplyUpdateDTO replyUpdateDTO = ReplyUpdateDTO.builder()
//                .replyId(replyId)
//                .replyAuthor(replyAuthor)
//                .replyContent(replyContent)
//                .build();
//
//        // When
//        replyRepository.update(replyUpdateDTO);
//        ReplyFindDTO reply = replyRepository.findByReplyId(replyId);
//
//        // Then
//        Assertions.assertEquals(replyAuthor, reply.getReplyAuthor());
//        Assertions.assertEquals(replyContent, reply.getReplyContent());
//        Assertions.assertTrue(reply.getUpdatedAt().isAfter(reply.getPublishedAt()));
//    }
//}
