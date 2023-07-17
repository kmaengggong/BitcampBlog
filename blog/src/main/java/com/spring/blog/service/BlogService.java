package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import org.springframework.data.domain.Page;

public interface BlogService {
    Page<Blog> findAll(Long pageNum);

    Blog findById(long blogId);

    void deleteById(long blogId);

    void save(Blog blog);

    void update(Blog blog);
}