package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepo extends JpaRepository<Friend,Long> {
    // userId를 fk로 가지는 User sendingUser 테이블을 userId로 추출
    List<Friend> findByFriendSendingUser_UserId(String userId);
    // userId를 fk로 가지는 User receivingUser 테이블을 userId로 추출
    List<Friend> findByFriendReceivingUser_UserId(String userId);
}
