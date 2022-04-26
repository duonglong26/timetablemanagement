package com.duogglong.tm.auditing;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({EntityAuditListener.class})
public class AuditableEntity implements Serializable {
    @Column(
            name = "create_date",
            nullable = false
    )
    private LocalDateTime createDate;
    @Column(
            name = "created_by",
            length = 100,
            nullable = false
    )
    private String createdBy;
    @Column(
            name = "modify_date",
            nullable = true
    )
    private LocalDateTime modifyDate;
    @Column(
            name = "modified_by",
            length = 100,
            nullable = true
    )
    private String modifiedBy;

    public AuditableEntity() {
    }

    public AuditableEntity(AuditableEntity entity) {
        if (entity != null) {
            this.modifiedBy = entity.modifiedBy;
            this.modifyDate = entity.modifyDate;
            this.createdBy = entity.createdBy;
            this.createDate = entity.createDate;
        }
    }

}
