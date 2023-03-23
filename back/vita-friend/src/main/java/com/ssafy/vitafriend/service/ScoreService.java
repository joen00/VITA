package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendRankDto;

public interface ScoreService {
    FriendRankDto getTotalScoreByUser(String userId);
}
