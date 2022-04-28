package com.duogglong.tm.entity;

import com.duogglong.tm.core.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "user_role")
public class UserRole extends BaseObject {
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public UserRole() {
    }

}

