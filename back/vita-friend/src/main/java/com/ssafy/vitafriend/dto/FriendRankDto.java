package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class FriendRankDto {

    private String userId;
    private String userImg;
    private String userNickname;
    private int totalScore;
//    private int totalScoreWeight;
    private int totalScoreEnergy;
    private int totalScoreRhr;
    private int totalScoreStress;
    private int totalScoreStep;
    private int totalScoreSleep;
}
