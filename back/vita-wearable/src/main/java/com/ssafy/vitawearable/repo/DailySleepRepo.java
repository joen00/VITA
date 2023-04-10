package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.DailySleep;
import com.ssafy.vitawearable.entity.DailyWearable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailySleepRepo extends JpaRepository<DailySleep,Long> {
    List<DailySleep> findByUser_UserId(String userId);
}
