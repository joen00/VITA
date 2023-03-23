package com.ssafy.vitafriend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInfoDto {

    protected String userNickname;
    protected String userId;
    protected String userImg;
    protected int userScore;
    protected String userHistoryImg;
    protected ZonedDateTime createdDate;

}
