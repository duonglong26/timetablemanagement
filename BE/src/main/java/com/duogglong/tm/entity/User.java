package com.duogglong.tm.entity;

import com.duogglong.tm.core.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tbl_user")
public class User extends BaseObject {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoleList;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<UserRole> userRoleList) {
        this.username = username;
        this.password = password;
        this.userRoleList = userRoleList;
    }
}
