package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendTotalRankDto;
import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.repository.ScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FriendTotalRankDto getTotalScoreByUser(String userId) {
        Score getUserScoreInfo = scoreRepository.findFirstByUser_userIdOrderByCreatedDateDesc(userId);
        System.out.println(getUserScoreInfo);
        // 친구 추가된 유저의 점수 정보가 있다면 계산하여 표시
        if(getUserScoreInfo != null) {
            FriendTotalRankDto userScoreInfo = modelMapper.map(getUserScoreInfo, FriendTotalRankDto.class);
            int totalScore = userScoreInfo.getTotalScoreEnergy() +
                    userScoreInfo.getTotalScoreSleep() + userScoreInfo.getTotalScoreRhr() +
                    userScoreInfo.getTotalScoreStep() + userScoreInfo.getTotalScoreStress();
            userScoreInfo.setTotalScore(totalScore/5);

            return userScoreInfo;
        }
        // 친구 추가된 유저의 점수 정보가 없다면 기본값이 모두 0으로 표시됨
        else {
            FriendTotalRankDto noUserScoreInfo = new FriendTotalRankDto();
            noUserScoreInfo.setTotalScore(0);
            noUserScoreInfo.setTotalScoreStep(0);
            noUserScoreInfo.setTotalScoreEnergy(0);
            noUserScoreInfo.setTotalScoreStress(0);
            noUserScoreInfo.setTotalScoreRhr(0);
            noUserScoreInfo.setTotalScoreSleep(0);
            return noUserScoreInfo;
        }
    }

}
