package com.duogglong.tm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuditableEntityDto implements Serializable {
    protected LocalDateTime createDate;
    protected String createdBy;
    protected LocalDateTime modifyDate;
    protected String modifiedBy;

    public AuditableEntityDto() {
    }
}
