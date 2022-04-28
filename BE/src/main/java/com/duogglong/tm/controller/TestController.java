package com.duogglong.tm.controller;

import com.duogglong.tm.core.entity.SampleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @GetMapping
    public String home() {
        return "Home";
    }

    @GetMapping("/1")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You can only see this after a successful login.");
    }

    @GetMapping("/2")
    public ResponseEntity<SampleResponse<String>> test2() {
//        Role role = null;
//        System.out.println(role.getName());
//        SampleResponse<String> result = new SampleResponse<>(200, null, "123");
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), null, "123"), HttpStatus.OK);
    }
}
