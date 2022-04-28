package com.duogglong.tm.dto;

import com.duogglong.tm.core.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto extends BaseObjectDto {
    String accessToken;
    String refreshToken;

    public TokenDto() {
    }

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
