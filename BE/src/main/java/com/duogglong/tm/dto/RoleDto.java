package com.duogglong.tm.dto;

import com.duogglong.tm.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends BaseObjectDto {
    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public RoleDto(Role entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.active = entity.getActive();
            this.createDate = entity.getCreateDate();
            this.createdBy = entity.getCreatedBy();
            this.modifyDate = entity.getModifyDate();
            this.modifiedBy = entity.getModifiedBy();
            this.name = entity.getName();
        }
    }
}
