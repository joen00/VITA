package com.ssafy.vitauser.repository.mypage;

import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.entity.user.UserBadge;
import com.ssafy.vitauser.entity.user.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    List<UserHistory> findAllByUser(User user);

}
