package com.ssafy.vitafriend.repository;

import com.ssafy.vitafriend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
    User findByUserNickname(String userNickname);
    List<User> findAll();
}
