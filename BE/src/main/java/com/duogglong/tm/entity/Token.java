package com.duogglong.tm.entity;

import com.duogglong.tm.core.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_token")
public class Token extends BaseObject {
    String accessToken;
    String refreshToken;

    public Token() {
    }

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
