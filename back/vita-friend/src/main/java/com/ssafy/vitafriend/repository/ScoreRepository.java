package com.ssafy.vitafriend.repository;

import com.ssafy.vitafriend.dto.FriendTotalRankDto;
import com.ssafy.vitafriend.dto.UserInfoDto;
import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findFirstByUser_userIdOrderByCreatedDateDesc(String userId);
}
