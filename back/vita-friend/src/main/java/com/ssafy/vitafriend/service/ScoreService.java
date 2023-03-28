package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendTotalRankDto;
import com.ssafy.vitafriend.entity.Score;

public interface ScoreService {
    FriendTotalRankDto getTotalScoreByUser(String userId);
}
