package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.UserAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAverageRepo extends JpaRepository<UserAverage,Long> {
    // userId로 해당 유저 평균 리스트 추출
    List<UserAverage> findByUser_UserId(String userId);
}
