package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.ExtraInfoDto;
import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    public User putUserUpdate(ExtraInfoDto extraInfoDto, String userId) {
        User user = userRepository.findByUserId(userId);
        if (extraInfoDto.getNickname() != null) {
            user.setUserNickname(extraInfoDto.getNickname());
        }
        if (extraInfoDto.getAge() != 0) {
            user.setUserAge(extraInfoDto.getAge());
        }
        if (extraInfoDto.getGender() != null) {
            user.setUserGender(extraInfoDto.getGender());
        }
        if (extraInfoDto.getHeight() != 0) {
            user.setUserHeight(extraInfoDto.getHeight());
        }
        if (extraInfoDto.getWeight() != 0) {
            user.setUserWeight(extraInfoDto.getWeight());
        }
        if (extraInfoDto.getPhoneType() != null) {
            user.setUserPhoneType(extraInfoDto.getPhoneType());
        }

        user.setUserPublic(extraInfoDto.isUserPublic());

        try {
            userRepository.save(user);
        } catch (Exception e) {};

        return user;
    }

    public Boolean validateDuplicatedNickname(String nickname) {
        // 중복 O
        if (userRepository.findByUserNickname(nickname) != null) {
            return true;
        }
        // 중복 X
        else return false;
    }
}
