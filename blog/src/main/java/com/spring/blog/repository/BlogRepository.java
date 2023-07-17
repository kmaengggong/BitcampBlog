package com.spring.blog.repository;

import com.spring.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogRepository {
    void createBlogTable();
    void insertTestData();
    void dropBlogTable();
    List<Blog> findAll();
    Blog findById(long blogId);
    void save(Blog blog);
    void deleteById(long blogId);
    void update(Blog blog);
}
