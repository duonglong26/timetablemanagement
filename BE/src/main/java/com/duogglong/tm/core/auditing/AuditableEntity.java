package com.duogglong.tm.core.auditing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AuditableEntity implements Serializable {
    @Column(name = "create_date")
    @Temporal(TIMESTAMP)
    @CreatedDate
    private Date createDate;

    @Column(name = "created_by", length = 100)
    @CreatedBy
    private String createdBy;

    @Column(name = "modify_date")
    @Temporal(TIMESTAMP)
    @LastModifiedDate
    private Date modifyDate;

    @Column(name = "modified_by", length = 100)
    @LastModifiedBy
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
