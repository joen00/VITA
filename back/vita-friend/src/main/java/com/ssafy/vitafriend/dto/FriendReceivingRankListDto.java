package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendReceivingRankListDto {

    private String userId;
    private String userImg;
    private String userNickname;
    private int totalScore;

}
