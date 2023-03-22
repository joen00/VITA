package com.ssafy.vitauser.repository.mypage;

import com.ssafy.vitauser.entity.user.Badge;
import com.ssafy.vitauser.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository  extends JpaRepository<Badge, Integer> {
}
