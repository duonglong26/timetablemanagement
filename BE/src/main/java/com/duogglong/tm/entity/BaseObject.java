package com.duogglong.tm.entity;

import com.duogglong.tm.auditing.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseObject extends AuditableEntity {
//    @Transient
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "active"
    )
    private Boolean active;

    public BaseObject() {
    }

    public BaseObject(BaseObject object) {
        if (object != null) {
            this.id = object.getId();
        }
    }
}
