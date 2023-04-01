package com.ssafy.vitauser.service.mypage;


import com.ssafy.vitauser.dto.mypage.*;
import com.ssafy.vitauser.entity.user.*;
import com.ssafy.vitauser.repository.mypage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final UserUploadRepository userUploadRepository;

    @Transactional
    public UserInfoResponseDto selectOne(String id){
        User user = mypageRepository.findByUserId(id);
        UserInfoResponseDto userInfoResponseDto =UserInfoResponseDto.builder()
                .userId(user.getUserId())
                .userAge(user.getUserAge())
                .userGender(user.getUserGender())
                .userHeight(user.getUserHeight())
                .userImg(user.getUserImg())
                .userName(user.getUserName())
                .userNickname(user.getUserNickname())
                .userPhoneType(user.getUserPhoneType())
                .userPublic(user.isUserPublic())
                .userWeight(user.getUserWeight())
                .userScore(user.getUserScore())
                .userAvatar(user.getUserAvatar())
                .build();
        return userInfoResponseDto;
    }

    @Transactional
    public List<UserBadgeResponseDto> selectAllBadge(String id){
        User user = mypageRepository.findByUserId(id);
        // 회원에 해당하는 userBadge가 나온다.
        List<UserBadge> userBadgesList = userBadgeRepository.findAllByUser(user);
        List<UserBadgeResponseDto> userBadgeResponseList = new ArrayList<>();

        for (UserBadge ub : userBadgesList) {
            UserBadgeResponseDto userBadgeResponseDto = UserBadgeResponseDto.builder()
                    .userBadgeId(ub.getUserBadgeId())
                    .userBadgeGet(ub.isUserBadgeGet())
                    .badgeId(ub.getBadge().getBadgeId())
                    .build();
            System.out.println(userBadgeResponseDto);
            userBadgeResponseList.add(userBadgeResponseDto);
        }
        return userBadgeResponseList;
    }

    @Transactional
    public List<UserHistoryResponseDto> selectAllHistory(String id){
        User user = mypageRepository.findByUserId(id);
        // 회원에 해당하는 userBadge가 나온다.
        List<UserHistory> userHistoryList = userHistoryRepository.findAllByUser(user);
        List<UserHistoryResponseDto> userHistoryResponseList = new ArrayList<>();

        for (UserHistory uh : userHistoryList) {
            UserHistoryResponseDto userHistoryResponseDto = UserHistoryResponseDto.builder()
                    .userHistoryImg(uh.getUserHistoryImg())
                    .createdDate(uh.getCreatedDate())
                    .build();
            userHistoryResponseList.add(userHistoryResponseDto);
        }
        return userHistoryResponseList;
    }

    @Transactional
    public boolean updateUpload(String userId, String url) {
        User user = mypageRepository.findByUserId(userId);
        UserUpload updateUserUpload = userUploadRepository.findByUser(user);

        if (updateUserUpload != null){
            updateUserUpload.updateUpload(updateUserUpload.getUserUploadId(), user, url);
        }else {
            // 회원에 해당하는 userBadge가 나온다.
            List<UserBadge> userBadgesList = userBadgeRepository.findAllByUser(user);
            for(UserBadge userBadge : userBadgesList){
                // 3번 뱃지 true로 바꿔주기
                if (userBadge.getBadge().getBadgeId()==3){
                    Long userBadgeId = userBadge.getUserBadgeId();
                    UserBadge u= userBadgeRepository.findByUserBadgeId(userBadgeId);
                    u.updateUserBadge(userBadgeId,true,user,userBadge.getBadge());
                }
            }

            UserUploadRequestDto userUploadRequestDto = new UserUploadRequestDto(user, url);
            userUploadRepository.save(userUploadRequestDto.ToEntity());
        }
        return true;

    }

    @Transactional
    public boolean updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto, String userId) {
        User user = mypageRepository.findByUserId(userId);

        if (user != null) {
            user.setUserAge(userInfoUpdateRequestDto.getUserAge());
            user.setUserHeight(userInfoUpdateRequestDto.getUserHeight());
            user.setUserWeight(userInfoUpdateRequestDto.getUserWeight());
            user.setUserPhoneType(userInfoUpdateRequestDto.getUserPhoneType());
            mypageRepository.save(user);
            return true;
        }
        return false;
    }
}
