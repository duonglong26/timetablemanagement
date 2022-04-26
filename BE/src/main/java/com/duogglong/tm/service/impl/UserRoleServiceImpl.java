package com.duogglong.tm.service.impl;

import com.duogglong.tm.dto.UserRoleDto;
import com.duogglong.tm.entity.Role;
import com.duogglong.tm.entity.User;
import com.duogglong.tm.entity.UserRole;
import com.duogglong.tm.repository.RoleRepository;
import com.duogglong.tm.repository.UserRepository;
import com.duogglong.tm.repository.UserRoleRepository;
import com.duogglong.tm.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRole saveUserRole(long userId, long roleId) {
        UserRole result = new UserRole();
        User userRes = userRepository.findById(userId).orElse(null);
        Role roleRes = roleRepository.findById(roleId).orElse(null);
        if (userRes != null && roleRes != null) {
            result.setUser(userRes);
            result.setRole(roleRes);
            result = userRoleRepository.save(result);
            return result;
        }
        return null;
    }

    @Override
    public UserRoleDto saveUserRole(UserRoleDto dto) {
        if (dto != null && dto.getUser() != null && dto.getRole() != null) {
            UserRole userRole = userRoleRepository.findById(dto.getId()).orElse(null);
            if (userRole == null) {
                userRole = new UserRole();
            }
            User userRes = userRepository.findById(dto.getUser().getId()).orElse(null);
            Role roleRes = roleRepository.findById(dto.getRole().getId()).orElse(null);
            if (userRes != null && roleRes != null) {
                userRole.setUser(userRes);
                userRole.setRole(roleRes);
                userRole = userRoleRepository.save(userRole);
                return new UserRoleDto(userRole);
            }
        }
        return null;
    }
}
