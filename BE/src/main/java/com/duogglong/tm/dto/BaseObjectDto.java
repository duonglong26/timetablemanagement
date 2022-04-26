package com.duogglong.tm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseObjectDto extends AuditableEntityDto {
    protected long id;
    protected Boolean active;

    public BaseObjectDto() {
    }
}
