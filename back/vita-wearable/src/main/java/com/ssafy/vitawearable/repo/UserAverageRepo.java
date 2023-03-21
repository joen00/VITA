package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.UserAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAverageRepo extends JpaRepository<UserAverage,Long> {
    List<UserAverage> findByUser_UserId(String userId);
}
