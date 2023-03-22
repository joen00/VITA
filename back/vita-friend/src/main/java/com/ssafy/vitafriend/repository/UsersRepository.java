package com.ssafy.vitafriend.repository;

import com.ssafy.vitafriend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(String userId);
    Users findByUserNickname(String userNickname);
    List<Users> findAll();
}
