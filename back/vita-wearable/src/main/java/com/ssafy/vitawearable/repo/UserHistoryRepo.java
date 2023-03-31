package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepo extends JpaRepository<UserHistory,Long> {
    // userId로 UserHistory 리스트 추출. 최근 export 날짜 추출용도
    List<UserHistory> findByUser_UserId(String userId);

    // userHistory에 정보 저장
    UserHistory save(UserHistory userHistory);
}
