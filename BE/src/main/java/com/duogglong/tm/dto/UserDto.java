package com.duogglong.tm.dto;

import com.duogglong.tm.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto extends BaseObjectDto {
    private String username;
    private String password;
    private List<RoleDto> roleList = new ArrayList<>();

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(User entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.active = entity.getActive();
            this.createDate = entity.getCreateDate();
            this.createdBy = entity.getCreatedBy();
            this.modifyDate = entity.getModifyDate();
            this.modifiedBy = entity.getModifiedBy();
            this.username = entity.getUsername();
            if (!CollectionUtils.isEmpty(entity.getUserRoleList())) {
                entity.getUserRoleList().forEach(userRole -> {
                    this.roleList.add(new RoleDto(userRole.getRole()));
                });
            }
        }
    }
}
