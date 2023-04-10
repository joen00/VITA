package com.ssafy.vitafriend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendEnergyRankDto {
    private String userId;
    private String userImg;
    private String userNickname;
    private int totalScoreEnergy;
}
