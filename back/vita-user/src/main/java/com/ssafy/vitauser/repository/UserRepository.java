package com.ssafy.vitauser.repository;

import com.ssafy.vitauser.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User findByUserNickname(String userNickname);
}
