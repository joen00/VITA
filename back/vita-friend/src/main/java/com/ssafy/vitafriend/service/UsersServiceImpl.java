package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.entity.User;
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
    public User findByUserId(String userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }
}
