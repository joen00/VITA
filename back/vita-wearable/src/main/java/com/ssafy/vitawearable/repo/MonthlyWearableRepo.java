package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.dto.WeightMonthlyDto;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyWearableRepo extends JpaRepository<MonthlyWearable,Long> {
    // userId로 해당유저의 월간 데이터 추출
    List<MonthlyWearable> findByUser_UserId(String userId);
}
