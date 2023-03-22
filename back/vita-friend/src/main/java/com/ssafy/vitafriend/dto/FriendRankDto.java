package com.ssafy.vitafriend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class FriendRankDto {
    private String userNickname;
    private int userScore;

}
