package com.example.demo.exception;

public class ContentException extends RuntimeException{
    public ContentException() {
        super("评论不能为空");
    }
}
