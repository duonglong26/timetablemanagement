package com.duogglong.tm.service.impl;

import com.duogglong.tm.dto.RoleDto;
import com.duogglong.tm.entity.Role;
import com.duogglong.tm.repository.RoleRepository;
import com.duogglong.tm.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleDto saveOrUpdate(RoleDto dto) {
        if (dto != null) {
            Role role = roleRepository.findById(dto.getId()).orElse(null);
            if (role == null) {
                role = new Role();
            }
            role.setName(dto.getName());
            log.info("Saving new role {} to the database", dto.getName());
            role = roleRepository.save(role);
            return new RoleDto(role);
        }
        return null;
    }
}