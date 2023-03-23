package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendRankDto;
import com.ssafy.vitafriend.dto.ScoreDto;
import com.ssafy.vitafriend.dto.UserInfoDto;
import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;
import com.ssafy.vitafriend.repository.ScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        int totalScore = userScoreInfo.getTotalScoreEnergy() + userScoreInfo.getTotalScoreWeight() +
                userScoreInfo.getTotalScoreSleep() + userScoreInfo.getTotalScoreRhr() +
                userScoreInfo.getTotalScoreStep() + userScoreInfo.getTotalScoreStress();
        userScoreInfo.setTotalScore(totalScore/6);

        return userScoreInfo;
    }

}
