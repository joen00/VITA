package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendListDto {
    private String userNickname;
    private String userId;
    private String userImg;
    private String userHistoryImg;
    private ZonedDateTime createdDate;
}
