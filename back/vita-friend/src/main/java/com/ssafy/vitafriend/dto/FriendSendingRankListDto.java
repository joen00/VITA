package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FriendSendingRankListDto {

    private String userId;
    private String userImg;
    private String userNickname;
    private int totalScore;

}
