package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.FriendRankDto;
import com.ssafy.vitafriend.dto.ScoreDto;
import com.ssafy.vitafriend.dto.UserInfoDto;
import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;

import java.util.List;

public interface ScoreService {
    FriendRankDto getTotalScoreByUser(String userId);
}
