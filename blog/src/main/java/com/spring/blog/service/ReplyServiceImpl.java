package com.spring.blog.service;

//import com.spring.blog.dto.ReplyResponseDTO;
//import com.spring.blog.dto.ReplyCreateRequestDTO;
//import com.spring.blog.dto.ReplyUpdateRequestDTO;
import com.spring.blog.entity.Reply;
import com.spring.blog.repository.ReplyJPARepository;
import com.spring.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    ReplyRepository replyRepository;
    ReplyJPARepository replyJPARepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository, ReplyJPARepository replyJPARepository){
        this.replyRepository = replyRepository;
        this.replyJPARepository = replyJPARepository;
    }

    @Override
    public List<Reply> findAllByBlogId(long blogId) {
//        return  replyRepository.findAllByBlogId(blogId);
        return replyJPARepository.findAllByBlogId(blogId);
    }

    @Override
    public Reply findByReplyId(long replyId) {
//        return replyRepository.findByReplyId(replyId);
        return replyJPARepository.findById(replyId).get();
    }

    @Override
    public void deleteByReplyId(long replyId) {
//        replyRepository.deleteByReplyId(replyId);
        replyJPARepository.deleteById(replyId);
    }

    @Override
    public void save(Reply reply) {
//        replyRepository.save(replyInsertDTO);
        replyJPARepository.save(reply);
    }

    @Override
    public void update(Reply reply) {
//        replyRepository.update(replyUpdateDTO);
        Reply findReply = replyJPARepository.findById(reply.getReplyId()).get();

        findReply.setReplyContent(reply.getReplyContent());

        replyJPARepository.save(findReply);
    }
}