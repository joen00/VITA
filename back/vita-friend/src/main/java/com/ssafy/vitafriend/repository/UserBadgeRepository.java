package com.ssafy.vitafriend.repository;


import com.ssafy.vitafriend.entity.User;
import com.ssafy.vitafriend.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Integer> {

    List<UserBadge> findAllByUser(User user);
    UserBadge findByUserBadgeId(Long id);
}
