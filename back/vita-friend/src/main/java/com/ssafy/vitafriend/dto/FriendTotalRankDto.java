package com.ssafy.vitafriend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.vitafriend.entity.Score;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class FriendTotalRankDto {

    private String userId;
    private String userImg;
    private String userNickname;
    private int totalScore;
    @JsonIgnore
    private int totalScoreEnergy;
    @JsonIgnore
    private int totalScoreRhr;
    @JsonIgnore
    private int totalScoreStress;
    @JsonIgnore
    private int totalScoreStep;
    @JsonIgnore
    private int totalScoreSleep;

}
