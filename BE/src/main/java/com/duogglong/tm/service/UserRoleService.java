package com.duogglong.tm.service;

import com.duogglong.tm.dto.UserRoleDto;
import com.duogglong.tm.entity.UserRole;

public interface UserRoleService {
    UserRole saveUserRole(long userId, long roleId);
    UserRoleDto saveUserRole(UserRoleDto dto);
}
