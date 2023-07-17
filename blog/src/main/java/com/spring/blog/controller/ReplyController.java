package com.spring.blog.controller;

//import com.spring.blog.dto.ReplyResponseDTO;
//import com.spring.blog.dto.ReplyCreateRequestDTO;
//import com.spring.blog.dto.ReplyUpdateRequestDTO;
import com.spring.blog.entity.Reply;
import com.spring.blog.exception.NotFoundReplyByReplyIdException;
import com.spring.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService){
        this.replyService = replyService;
    }

    // 어떤 자원에 접근할것인지만 uri에 명시
    @RequestMapping(value = "/{blogId}/all", method = RequestMethod.GET)
    // rest서버는 응답시 응답코드와 응답객체를 넘기기 때문에 ResponseEntity를 리턴
    public ResponseEntity<List<Reply>> findAllReplies(@PathVariable long blogId){
        List<Reply> replyList = replyService.findAllByBlogId(blogId);

        return ResponseEntity.ok().body(replyList);
    }

    @RequestMapping(value = "/{replyId}",method = RequestMethod.GET)
    public ResponseEntity<?> findReply(@PathVariable long replyId){
        Reply reply = replyService.findByReplyId(replyId);

        try {
            if (reply == null){
                throw new NotFoundReplyByReplyIdException("없는 리플번호를 조회했습니다.");
            }

        }catch (NotFoundReplyByReplyIdException e){
            e.printStackTrace();
            return new ResponseEntity<>("찾는번호 없음",HttpStatus.NOT_FOUND);
        }
//        return  new ResponseEntity<ReplyFindByIdDTO>(reply, HttpStatus.OK);
        return ResponseEntity.ok(reply);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    // json이기 때문에 @RequestBody(json 데이터 역직렬화헤줌)가 필요하다
    public ResponseEntity<String> insertReply(@RequestBody Reply reply){
        replyService.save(reply);
        return ResponseEntity.ok("저장완료");
    }

    @RequestMapping(value = "/{replyId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReply(@PathVariable long replyId){
        replyService.deleteByReplyId(replyId);
        return ResponseEntity.ok("삭제 완료");
    }

    @RequestMapping(value = "/{replyId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> updateReply(@PathVariable long replyId, @RequestBody Reply reply){
        reply.setReplyId(replyId);

        replyService.update(reply);
        return ResponseEntity.ok("수정 완료");
    }
}