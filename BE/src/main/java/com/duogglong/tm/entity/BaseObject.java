package com.duogglong.tm.entity;

import com.duogglong.tm.core.auditing.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseObject extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "active"
    )
    private Boolean active;

    public BaseObject() {
    }
}
