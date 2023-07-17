package com.spring.blog.dto;

import com.spring.blog.entity.Reply;
import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class ReplyUpdateDTO {
    private long replyId;
    private String replyWriter;
    private String replyContent;

    public ReplyUpdateDTO(Reply reply){
        this.replyId = reply.getReplyId();
        this.replyWriter = reply.getReplyWriter();
        this.replyContent = reply.getReplyContent();
    }

    public ReplyInsertDTO toReplyInsertDTO(){
        return new ReplyInsertDTO(
                this.getReplyId(),
                this.getReplyWriter(),
                this.getReplyContent()
        );
    }
}
