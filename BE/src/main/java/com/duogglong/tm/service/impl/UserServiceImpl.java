package com.duogglong.tm.service.impl;

import com.duogglong.tm.dto.RoleDto;
import com.duogglong.tm.dto.UserDto;
import com.duogglong.tm.entity.Role;
import com.duogglong.tm.entity.User;
import com.duogglong.tm.repository.UserRepository;
import com.duogglong.tm.repository.UserRoleRepository;
import com.duogglong.tm.service.UserRoleService;
import com.duogglong.tm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.duogglong.tm.config.PasswordConfig.passwordEncoder;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    static UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            List<Role> roleList = userRoleRepository.findByUserId(user.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDto saveOrUpdate(UserDto dto) {
        if (dto != null) {
            User user = userRepository.findById(dto.getId()).orElse(null);
            if (user == null) {
                user = new User();
            }
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder().encode(dto.getPassword()));
            log.info("Saving new user {} to the database", dto.getUsername());
            user = userRepository.save(user);

//            Save userRole
            if (!CollectionUtils.isEmpty(dto.getRoleList())) {
                user.setUserRoleList(new ArrayList<>());
                for (RoleDto role : dto.getRoleList()) {
                    user.getUserRoleList().add(userRoleService.saveUserRole(user.getId(), role.getId()));
                }
            }
            return new UserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> listUser = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        listUser.forEach(user -> {
            result.add(new UserDto(user));
        });
        return result;
    }

    @Override
    public UserDto getUserById(long userId) {
        return new UserDto(userRepository.findById(userId).orElse(null));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return new UserDto(userRepository.findByUsername(username));
    }

    @Override
    public boolean checkExitsAccount(String username) {
        if (userRepository.findByUsername(username) != null) {
            return true;
        }
        return false;
    }

}