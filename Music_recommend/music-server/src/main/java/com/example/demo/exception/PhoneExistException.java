package com.example.demo.exception;

public class PhoneExistException extends  RuntimeException{
    public PhoneExistException() {
        super("手机号异常");
    }
}
