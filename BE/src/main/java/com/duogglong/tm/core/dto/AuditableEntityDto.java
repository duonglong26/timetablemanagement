package com.duogglong.tm.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AuditableEntityDto implements Serializable {
    protected Date createDate;
    protected String createdBy;
    protected Date modifyDate;
    protected String modifiedBy;

    public AuditableEntityDto() {
    }

    public AuditableEntityDto(Date createDate, String createdBy, Date modifyDate, String modifiedBy) {
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.modifyDate = modifyDate;
        this.modifiedBy = modifiedBy;
    }
}
