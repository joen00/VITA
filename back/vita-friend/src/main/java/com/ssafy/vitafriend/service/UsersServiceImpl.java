package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.entity.Users;
import com.ssafy.vitafriend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users findByUserId(String userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }
}
