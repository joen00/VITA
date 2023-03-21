package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.DailyWearable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyWearableRepo extends JpaRepository<DailyWearable,Long> {
    List<DailyWearable> findByUser_UserId(String userId);
}
