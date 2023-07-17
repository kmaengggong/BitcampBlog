package com.spring.blog.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConnectionTestRepository {
    String getNow();
}
