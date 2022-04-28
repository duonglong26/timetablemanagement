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
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.duogglong.tm.core.listener.ApplicationStartupListener.passwordEncoder;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
    }

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
            roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDto saveOrUpdate(UserDto dto) {
        if (dto != null) {
            User user = userRepository.findByUsername(dto.getUsername());
            if (user == null) {
                user = new User();
            }
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder().encode(dto.getPassword()));
            log.info("Saving new user {} to the database", dto.getUsername());
            user = userRepository.save(user);

//            Save userRole
            if (!CollectionUtils.isEmpty(dto.getRoleList())) {
                user.setUserRoleList(new LinkedList<>());
                for (RoleDto role : dto.getRoleList()) {
                    user.getUserRoleList().add(userRoleService.saveUserRole(user.getId(), role.getId()));
                }
            }
            return new UserDto(user);
        }
        throw new NoSuchFieldError("No blank piece found!");
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> listUser = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        listUser.forEach(user -> result.add(new UserDto(user)));
        return result;
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.isNull(user)) {
            throw new ObjectNotFoundException(UserServiceImpl.class,
                    "Not found user with id " + userId + " in database.");
        }
        return new UserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return new UserDto(userRepository.findByUsername(username));
    }

    @Override
    public Boolean checkExitsAccount(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public Boolean deleteById(long id) {
        if (userRepository.existsById(id)) {
            userRoleRepository.deleteByIdUser(id);
            userRepository.deleteById(id);
            return true;
        }
        throw new ObjectNotFoundException(UserServiceImpl.class, "Not found user in database.");
    }

}