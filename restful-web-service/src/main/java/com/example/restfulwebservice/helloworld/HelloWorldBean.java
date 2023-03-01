package com.example.restfulwebservice.helloworld;

// lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data       // getter & setteer
@AllArgsConstructor     // 생성자 자동 생성
@NoArgsConstructor // Default 생성자가 자동으로 생성
public class HelloWorldBean {
    private String message;
}