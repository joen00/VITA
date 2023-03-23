package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScoreDto {

    private String userId;
    private String userNickname;
    private String userImg;
    private int totalScore;
    private int totalScoreWeight;
    private int totalScoreEnergy;
    private int totalScoreRhr;
    private int totalScoreStress;
    private int totalScoreStep;
    private int totalScoreSleep;

    public ScoreDto(User user, Score score) {
        this.userId = user.getUserId();
        this.userNickname = user.getUserNickname();
        this.totalScoreWeight = score.getTotalScoreWeight();
        this.totalScoreEnergy = score.getTotalScoreEnergy();
        this.totalScoreRhr = score.getTotalScoreRhr();
        this.totalScoreStress = score.getTotalScoreStress();
        this.totalScoreStep = score.getTotalScoreStep();
        this.totalScoreSleep = score.getTotalScoreSleep();
    }
}
