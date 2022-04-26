package com.duogglong.tm.listener;

import com.duogglong.tm.dto.RoleDto;
import com.duogglong.tm.dto.UserDto;
import com.duogglong.tm.dto.UserRoleDto;
import com.duogglong.tm.entity.User;
import com.duogglong.tm.repository.UserRepository;
import com.duogglong.tm.service.RoleService;
import com.duogglong.tm.service.UserRoleService;
import com.duogglong.tm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (Boolean.FALSE.equals(userService.checkExitsAccount("admin"))) {
            // create account admin
            RoleDto role = roleService.saveOrUpdate(new RoleDto("ROLE_ADMIN"));
            UserDto user = new UserDto(userRepository.save(new User("admin", "$2a$10$VTbOE3yFg04t3PLe/ArntuZbrJvNMIOX7NeSwdcZQ3447Iq9HY7HO")));
            userRoleService.saveUserRole(new UserRoleDto(role, user));
        }
    }
}
