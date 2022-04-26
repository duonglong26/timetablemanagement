package com.duogglong.tm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tbl_user")
public class User extends BaseObject {

    @Column(name = "username")
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
}

//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import javax.xml.bind.annotation.XmlRootElement;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Table(name = "tbl_user")
//@XmlRootElement
//@Entity
//public class User extends BaseObject implements UserDetails {
//    private static final long serialVersionUID = 4572941405687566992L;
//    @Column(
//            name = "username",
//            length = 100,
//            nullable = false,
//            unique = true
//    )
//    private String username;
//    @Column(
//            name = "password",
//            nullable = false
//    )
//    private String password;
//    @Column(
//            name = "just_created",
//            nullable = false
//    )
//    private Boolean justCreated = true;
//    @Column(
//            name = "last_login_time",
//            nullable = true
//    )
//    private Date lastLoginTime;
//    @Column(
//            name = "total_login_failures",
//            nullable = true
//    )
//    private Long totalLoginFailures;
//    @Column(
//            name = "last_login_failures",
//            nullable = true
//    )
//    private Long lastLoginFailures;
//    @Column(
//            name = "email",
//            length = 150,
//            nullable = true,
//            unique = false
//    )
//    private String email;
//    @ManyToMany(
//            fetch = FetchType.EAGER
//    )
//    @Fetch(FetchMode.SELECT)
//    @JoinTable(
//            name = "tbl_user_role",
//            joinColumns = {@JoinColumn(
//                    name = "user_id"
//            )},
//            inverseJoinColumns = {@JoinColumn(
//                    name = "role_id"
//            )}
//    )
//    private Set<Role> roles = new HashSet();
//    @Transient
//    private Boolean changePassword = false;
//    @Transient
//    private String confirmPassword;
//    @Column(
//            name = "active",
//            nullable = false
//    )
//    private Boolean active = true;
//    @Column(
//            name = "account_non_expired",
//            nullable = true
//    )
//    private Boolean accountNonExpired = true;
//    @Column(
//            name = "account_non_locked",
//            nullable = true
//    )
//    private Boolean accountNonLocked = true;
//    @Column(
//            name = "credentials_non_expired",
//            nullable = true
//    )
//    private Boolean credentialsNonExpired = true;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> authorities =
//                new HashSet<>();
//        this.roles.forEach(role -> {
//            authorities.add((GrantedAuthority) role);
//        });
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.active;
//    }
//
//
//}
