//package com.spring.blog.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.blog.dto.ReplyFindDTO;
//import com.spring.blog.dto.ReplyInsertDTO;
//import com.spring.blog.repository.ReplyRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//// Controller 테스트는 브라우저를 켜야 테스트가 가능하므로(서버에 url만 입력하면 동작)
//// Controller 객체를 따로 생성하지 않고, 브라우저를 대체할 MockMvc 객체를 만들어 수행
//@AutoConfigureMockMvc
//public class ReplyControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    // 임시적으로 ReplyRepository 생성
//    // Repository Layer의 메서드는 쿼리문 하나만 호출하는 것이 보장되지만,
//    // Service Layer의 메서드는 추후에 쿼리문을 두 개 이상 호출할 수도 있고,
//    // 그런 변경이 생겼을 때 테스트 코드도 같이 수정할 가능성이 생김
//    @Autowired
//    private ReplyRepository replyRepository;
//
//    @BeforeEach
//    public void setMockMvc(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("2번 글의 전체 댓글을 조회")
//    public void findAllRepliesTest() throws Exception{
//        // Given
//        String url = "/reply/2/all";
//        String replyAuthor = "1번유저";
//        long replyId = 1;
//
//        // When
//        // json을 받을 수 있는 ResultActions 자료형
//        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .get(url)  // url 주소로 get 요청 넣기
//                .accept(MediaType.APPLICATION_JSON)  // 리턴 자료가 JSON임을 명시
//        );
//
//        // Then
//        result
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].replyAuthor").value(replyAuthor))
//                .andExpect(jsonPath("$[0].replyId").value(replyId));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("2번 댓글 조회")
//    public void findReplyTest() throws Exception{
//        // Given
//        String url = "/reply/2";
//        String replyAuthor = "2번유저";
//        long replyId = 2;
//
//        // When
//        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .get(url)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//
//        // Then
//        result
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.replyAuthor").value(replyAuthor))
//                .andExpect(jsonPath("$.replyId").value(replyId));
//
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("1번 글에 댓글 추가")
//    public void insertReplyTest() throws Exception{
//        // Given
//        String insertUrl ="/reply";
//        String findUrl = "/reply/1/all";
//        long blogId = 1;
//        String replyAuthor = "2번유저";
//        String replyContent = "ㅁㄴㅇㄹ";
//
//        ReplyInsertDTO replyInsertDTO = ReplyInsertDTO.builder()
//                .blogId(blogId)
//                .replyAuthor(replyAuthor)
//                .replyContent(replyContent)
//                .build();
//
//        // 데이터 직렬화
//        final String requestBody = objectMapper.writeValueAsString(replyInsertDTO);
//
//        // When
//        mockMvc.perform(MockMvcRequestBuilders
//                .post(insertUrl)  // reply 주소에 post 방식으로 요청을 넣고
//                .contentType(MediaType.APPLICATION_JSON)  // JSON으로 받고
//                .content(requestBody)  // 위에 직렬화한 requestBody 변수를 전달
//        );
//
//        // Then
//        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders
//                .get(findUrl)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//
//        result
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].replyAuthor").value(replyAuthor))
//                .andExpect(jsonPath("$[0].replyContent").value(replyContent));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("3번 댓글 삭제")
//    public void deleteReplyTest() throws Exception{
//        // Given
//        String url = "/reply/3";
//        long replyId = 3;
//        long blogId = 2;
//
//        // When
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete(url)
//                .accept(MediaType.TEXT_PLAIN)
//        );
//
//        // Then
//        List<ReplyFindDTO> replyFindDTOList = replyRepository.findAllByBlogId(blogId);
//        Assertions.assertEquals(2, replyFindDTOList.size());
//        ReplyFindDTO replyFindDTO = replyRepository.findByReplyId(replyId);
//        Assertions.assertNull(replyFindDTO);
//    }
//}
