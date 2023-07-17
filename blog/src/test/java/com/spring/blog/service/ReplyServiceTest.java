//package com.spring.blog.service;
//
//import com.spring.blog.dto.ReplyFindDTO;
//import com.spring.blog.dto.ReplyInsertDTO;
//import com.spring.blog.dto.ReplyUpdateDTO;
//import com.spring.blog.entity.Reply;
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
//public class ReplyServiceTest {
//    @Autowired
//    private ReplyService replyService;
//
//    @Test
//    @Transactional
//    @DisplayName("2번 글의 모든 댓글 가져옴")
//    public void findAllByBlogIdTest(){
//        // Given
//        long blogId = 2;
//
//        // When
//        List<Reply> replyList = replyService.findAllByBlogId(blogId);
//
//        // Then
//        Assertions.assertEquals(3, replyList.size());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("1번 댓글 가져옴")
//    public void findByReplyIdTest(){
//        // Given
//        long replyId = 1;
//        String replyAuthor = "1번유저";
//        String replyContent = "이게 글인가요?";
//
//        // When
//        Reply reply = replyService.findByReplyId(replyId);
//
//        // Then
//        Assertions.assertEquals(replyId, reply.getReplyId());
//        Assertions.assertEquals(replyAuthor, reply.getReplyAuthor());
//        Assertions.assertEquals(replyContent, reply.getReplyContent());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("3번 댓글 삭제")
//    public void deleteByReplyIdTest(){
//        // Given
//        long replyId = 3;
//
//        // When
//        replyService.deleteByReplyId(replyId);
//
//        // Then
//        Assertions.assertNull(replyService.findByReplyId(replyId));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("1번 글에 새로운 댓글 추가")
//    public void saveTeset(){
//        // Given
//        long blogId = 1;
//        String replyAuthor = "new유저";
//        String replyContent = "새로운 댓글";
//        Reply reply = Reply.builder()
//                .blogId(blogId)
//                .replyAuthor(replyAuthor)
//                .replyContent(replyContent)
//                .build();
//
//        // When
//        replyService.save(reply);
//        List<Reply> replyList = replyService.findAllByBlogId(blogId);
//        Reply reply1 = replyList.get(replyList.size()-1);
//
//        // Then
//        Assertions.assertEquals(1, replyList.size());
//        Assertions.assertEquals(replyAuthor, reply1.getReplyAuthor());
//        Assertions.assertEquals(replyContent, reply1.getReplyContent());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("1번 댓글 수정")
//    public void updateTest(){
//        // Given
//        long replyId = 1;
//        String replyAuthor = "수정유저";
//        String replyContent = "수정된 댓글";
//        Reply replyUpdateDTO = Reply.builder()
//                .replyId(replyId)
//                .replyAuthor(replyAuthor)
//                .replyContent(replyContent)
//                .build();
//
//        // When
//        replyService.update(replyUpdateDTO);
//        Reply reply = replyService.findByReplyId(replyId);
//
//        // Then
//        Assertions.assertEquals(replyAuthor, reply.getReplyAuthor());
//        Assertions.assertEquals(replyContent, reply.getReplyContent());
//        Assertions.assertTrue(reply.getUpdatedAt().isAfter(reply.getPublishedAt()));
//    }
//}
