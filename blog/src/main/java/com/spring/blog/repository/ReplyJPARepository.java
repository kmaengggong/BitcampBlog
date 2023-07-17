package com.spring.blog.repository;

import com.spring.blog.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyJPARepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByBlogId(long blogId);
    void deleteByBlogId(long blogId);
    void deleteAllByBlogId(long blogId);
}