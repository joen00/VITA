package com.ssafy.vitauser.service;

import com.ssafy.vitauser.dto.ExtraInfoDto;
import com.ssafy.vitauser.entity.user.Badge;
import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.entity.user.UserBadge;
import com.ssafy.vitauser.repository.UserRepository;
import com.ssafy.vitauser.repository.mypage.BadgeRepository;
import com.ssafy.vitauser.repository.mypage.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

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
        if (extraInfoDto.getUserAvatar() != null) {
            user.setUserAvatar(extraInfoDto.getUserAvatar());
        }

        user.setUserPublic(extraInfoDto.isUserPublic());

        try {
            /* 최초 회원가입 시 유저 뱃지 Init + SignUp 뱃지 Get */
            List<UserBadge> userBadgesList = userBadgeRepository.findAllByUser(user);

            if (userBadgesList.isEmpty()) {
                List<Badge> badgeList = badgeRepository.findAll();
                for (Badge badge : badgeList) {
                    UserBadge userBadge;

                    if (badge.getBadgeName().equals("signup")) {
                        userBadge = UserBadge.builder()
                                .userBadgeGet(true)
                                .user(user)
                                .badge(badge)
                                .build();
                    }
                    else {
                        userBadge = UserBadge.builder()
                                .userBadgeGet(false)
                                .user(user)
                                .badge(badge)
                                .build();
                    }
                    userBadgeRepository.save(userBadge);
                }
            }
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
