package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.dto.WeightMonthlyDto;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyWearableRepo extends JpaRepository<MonthlyWearable,Long> {
    List<MonthlyWearable> findByUser_UserId(String userId);
}
