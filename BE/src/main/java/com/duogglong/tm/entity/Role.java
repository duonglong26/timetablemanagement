package com.duogglong.tm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@Table(name = "tbl_role")
@XmlRootElement
@Entity
public class Role extends BaseObject {
    @Transient
    private static final long serialVersionUID = 6318192070978248463L;
    @Column(name = "role_name")
    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoleList;

    public Role() {
    }
}
