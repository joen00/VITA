package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendRankDto;
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
    public FriendRankDto getTotalScoreByUser(String userId) {
        Score getUserScoreInfo = scoreRepository.findByUser_userId(userId);
        FriendRankDto userScoreInfo = modelMapper.map(getUserScoreInfo, FriendRankDto.class);
        int totalScore = userScoreInfo.getTotalScoreEnergy() +
                userScoreInfo.getTotalScoreSleep() + userScoreInfo.getTotalScoreRhr() +
                userScoreInfo.getTotalScoreStep() + userScoreInfo.getTotalScoreStress();
        userScoreInfo.setTotalScore(totalScore/5);

        return userScoreInfo;
    }

}
