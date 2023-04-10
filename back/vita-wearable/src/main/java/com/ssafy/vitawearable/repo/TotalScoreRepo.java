package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.TotalScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalScoreRepo extends JpaRepository<TotalScore,Long> {
    // userId로 해당 유저 총합 점수 리스트 추출
    List<TotalScore> findByUser_UserId(String userId);
}