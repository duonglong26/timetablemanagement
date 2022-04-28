package com.duogglong.tm.controller;

import com.duogglong.tm.core.entity.SampleResponse;
import com.duogglong.tm.dto.UserDto;
import com.duogglong.tm.service.UserService;
import com.duogglong.tm.utils.ServerConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController implements ApiController<UserDto> {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SampleResponse<UserDto>> createOrUpdate(@RequestBody UserDto dto) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), ServerConst.SUCCESS, service.saveOrUpdate(dto)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse<UserDto>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), ServerConst.SUCCESS, service.getUserById(Long.parseLong(id))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponse<Boolean>> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), ServerConst.SUCCESS, service.deleteById(Long.parseLong(id))), HttpStatus.OK);
    }

    @GetMapping("/is-exist/{username}")
    public ResponseEntity<SampleResponse<Boolean>> isExist(@PathVariable("username") String username) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.OK.value(), ServerConst.SUCCESS, service.checkExitsAccount(username)), HttpStatus.OK);
    }
}
