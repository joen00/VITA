package com.ssafy.vitauser.repository.mypage;

import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.entity.user.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {

    List<UserBadge> findAllByUser(User user);

}
