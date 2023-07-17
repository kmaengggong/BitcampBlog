package com.spring.blog.exception;

public class NotFoundBlogException extends RuntimeException{
    public NotFoundBlogException(String message){
        super(message);
    }
}
