package com.spring.blog.exception;

public class NotFoundReplyByReplyIdException extends RuntimeException{
    public NotFoundReplyByReplyIdException(String message){
        super(message);
    }
}
