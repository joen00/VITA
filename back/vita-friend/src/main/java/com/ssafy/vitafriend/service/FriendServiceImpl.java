package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;
import com.ssafy.vitafriend.dto.FriendReceivingListDto;
import com.ssafy.vitafriend.dto.FriendSendingListDto;
import com.ssafy.vitafriend.entity.Friend;
import com.ssafy.vitafriend.repository.FriendRepository;
import com.ssafy.vitafriend.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FriendSendingListDto> getSendingFriendList(String userId) {
        // 친구 추가된 유저 == friendStatus가 "accepted"인 유저
        return friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
    }

    @Override
    public List<FriendReceivingListDto> getReceivingFriendList(String userId) {
        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "accepted");
    }

    @Override
    public List<FriendReceivingListDto> getApplyingFriendList(String userId) {
        return friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "applied");
    }

    @Override
    public List<FriendSearchMapping> getSearchFriendList(String userId, String userNickname) {
        return friendRepository.getFriendSearchList(userId, userNickname);
    }

    @Override
    public void applyFriend(String userId, String userNickname) {
        // userId = 내 아이디, userNickname = 신청 보낼 유저의 닉네임
        Friend applySendInfo = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userNicknameAndFriendStatus(userId, userNickname, "accepted");
        Friend applyReceiveInfo = friendRepository.findByFriendSendingUser_userNicknameAndFriendReceivingUser_userIdAndFriendStatus(userNickname, userId, "accepted");

        FriendDto friend = new FriendDto();
        if (applySendInfo == null && applyReceiveInfo == null) {
            friend.setFriendSendingUser(usersRepository.findByUserId(userId));
            friend.setFriendReceivingUser(usersRepository.findByUserNickname(userNickname));
            friend.setFriendStatus("applied");
        }
        friendRepository.save(friend.toEntity());
    }

    @Override
    public void acceptFriend(String sendingUserId, String receivingUserId) {
        Friend currentRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userIdAndFriendStatus(sendingUserId, receivingUserId, "applied");
        currentRelation.acceptFriendRelation();
        friendRepository.save(currentRelation);
    }

    @Override
    public void rejectOrDeleteFriend(String sendingUserId, String receivingUserId) {
        Friend rejectOrDeleteRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userId(sendingUserId, receivingUserId);
        Friend rejectOrDeleteReverseRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userId(receivingUserId, sendingUserId);
        if (rejectOrDeleteRelation != null) {
            friendRepository.deleteById(rejectOrDeleteRelation.getFriendId());
        } else if (rejectOrDeleteReverseRelation != null) {
            friendRepository.deleteById(rejectOrDeleteReverseRelation.getFriendId());
        }
    }

}
