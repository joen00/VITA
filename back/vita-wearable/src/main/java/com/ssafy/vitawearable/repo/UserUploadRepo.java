package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.UserHistory;
import com.ssafy.vitawearable.entity.UserUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserUploadRepo extends JpaRepository<UserUpload,Long> {
    // userId로 UserUpload 리스트 추출. 최근 export 날짜 추출용도
    List<UserUpload> findByUser_UserId(String userId);
}
