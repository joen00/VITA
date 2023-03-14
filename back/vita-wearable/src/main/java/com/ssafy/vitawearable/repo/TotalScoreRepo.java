package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.TotalScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalScoreRepo extends JpaRepository<TotalScore,Long> {
    List<TotalScore> findByUsers_UserId(String userId);
}
