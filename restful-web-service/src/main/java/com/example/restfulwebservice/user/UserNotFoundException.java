package com.example.restfulwebservice.user;

// 200 -> OK
// 4XX -> Client

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)   // 4XX대 에러라고 명시, 만약 이게 없으면 500 에러로 나옴.
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
