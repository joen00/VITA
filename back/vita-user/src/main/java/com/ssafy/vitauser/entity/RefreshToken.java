package com.ssafy.vitauser.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    private String userId;
    private String tokenValue;

    @Builder
    public RefreshToken(String userId, String tokenValue) {
        this.userId = userId;
        this.tokenValue = tokenValue;
    }
}
