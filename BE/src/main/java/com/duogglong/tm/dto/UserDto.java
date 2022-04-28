package com.duogglong.tm.dto;

import com.duogglong.tm.core.dto.BaseObjectDto;
import com.duogglong.tm.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class UserDto extends BaseObjectDto {
    private String username;
    private String password;
    private List<RoleDto> roleList;

    public UserDto() {
    }

    public UserDto(String username, String password, List<RoleDto> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
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
                this.roleList = new LinkedList<>();
                entity.getUserRoleList().forEach(userRole -> this.roleList.add(new RoleDto(userRole.getRole())));
            }
        }
    }
}
