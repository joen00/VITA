package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.entity.Users;

import java.util.List;

public interface UsersService {
    Users findByUserId(String userId);
    List<Users> findAll();
}
