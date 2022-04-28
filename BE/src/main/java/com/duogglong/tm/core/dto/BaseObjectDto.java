package com.duogglong.tm.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseObjectDto extends AuditableEntityDto {
    protected long id;
    protected Boolean active;

    public BaseObjectDto() {
    }

    public BaseObjectDto(long id, Boolean active) {
        this.id = id;
        this.active = active;
    }
}
