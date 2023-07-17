package com.spring.blog.repository;

import com.spring.blog.dto.ReplyFindDTO;
import com.spring.blog.dto.ReplyInsertDTO;
import com.spring.blog.dto.ReplyUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyRepository {
    List<ReplyFindDTO> findAllByBlogId(long blogId);
    ReplyFindDTO findByReplyId(long replyId);
    void deleteByReplyId(long replyId);
    void deleteByBlogId(long blogId);
    void save(ReplyInsertDTO replyInsertDTO);
    void update(ReplyUpdateDTO replyUpdateDTO);
}
