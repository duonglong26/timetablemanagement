package com.duogglong.tm.service;

import com.duogglong.tm.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveOrUpdate(UserDto user);
    List<UserDto> getUsers();
    UserDto getUserById(long userId);
    UserDto getUserByUsername(String username);
    Boolean checkExitsAccount(String username);
    Boolean deleteById(long id);
}
