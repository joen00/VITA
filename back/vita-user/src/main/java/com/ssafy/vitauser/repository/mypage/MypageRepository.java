package com.ssafy.vitauser.repository.mypage;

import com.ssafy.vitauser.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<User, Integer> {

    User findByUserId(String id);

}
