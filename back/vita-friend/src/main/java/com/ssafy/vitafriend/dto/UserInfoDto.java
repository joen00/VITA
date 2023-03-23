package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserInfoDto {

    protected String userNickname;
    protected String userId;
    protected String userImg;
    protected int userScore;
    protected String userHistoryImg;
    protected ZonedDateTime createdDate;

    public User toUserEntity() {
        return User.builder().build();
    }
}
