package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.entity.User;

import java.util.List;

public interface UsersService {
    User findByUserId(String userId);
    List<User> findAll();
}
