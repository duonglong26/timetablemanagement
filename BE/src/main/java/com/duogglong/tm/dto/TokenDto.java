package com.duogglong.tm.dto;

import com.duogglong.tm.core.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private Set<String> roles;
    private String username;

    public TokenDto() {
    }

    public TokenDto(String accessToken, String refreshToken, Collection<GrantedAuthority> authorities, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        if (!CollectionUtils.isEmpty(authorities)) {
            this.roles = new HashSet<>();
            authorities.forEach(auth -> roles.add(auth.getAuthority()));
        }
        this.username = username;
    }

    public TokenDto(String accessToken, String refreshToken, List<RoleDto> roles, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        if (!CollectionUtils.isEmpty(roles)) {
            this.roles = new HashSet<>();
            roles.forEach(role -> this.roles.add(role.getName()));
        }
        this.username = username;
    }
}
