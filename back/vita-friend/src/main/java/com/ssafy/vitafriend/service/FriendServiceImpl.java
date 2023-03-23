package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;
import com.ssafy.vitafriend.dto.FriendReceivingListDto;
import com.ssafy.vitafriend.dto.FriendSendingListDto;
import com.ssafy.vitafriend.entity.Friend;
import com.ssafy.vitafriend.entity.User;
import com.ssafy.vitafriend.repository.FriendRepository;
import com.ssafy.vitafriend.repository.ScoreRepository;
import com.ssafy.vitafriend.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FriendSendingListDto> getSendingFriendList(String userId) {
        // 친구 추가된 유저 == friendStatus가 "accepted"인 유저
        return friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
    }

    @Override
    public List<FriendRankDto> getFriendRankList(String userId) {

        // 친구 신청 보내서 친구가 된 사람들 목록
        List<FriendSendingListDto> getFriendSendingList = friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
        // 친구 신청 받아서 친구가 된 사람들 목록
        List<FriendReceivingListDto> getFriendRecevingList = friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "accepted");

        // 친구 신청 보내서 친구가 된 사람들 의 점수 정보 불러오기
        List<FriendRankDto> friendSendingRankList = getFriendSendingList.stream().map(friend -> modelMapper.map(friend, FriendRankDto.class)).collect(Collectors.toList());
        for (FriendRankDto info : friendSendingRankList) {
            info.setTotalScore(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScore());
            info.setTotalScoreEnergy(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreEnergy());
            info.setTotalScoreRhr(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreRhr());
            info.setTotalScoreSleep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreSleep());
            info.setTotalScoreStep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStep());
            info.setTotalScoreStress(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStress());
        }
        // 친구 신청 받아서 친구가 된 사람들 의 점수 정보 불러오기
        List<FriendRankDto> friendReceivingRankList = getFriendRecevingList.stream().map(friend -> modelMapper.map(friend, FriendRankDto.class)).collect(Collectors.toList());
        for (FriendRankDto info : friendReceivingRankList) {
            info.setTotalScore(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScore());
            info.setTotalScoreEnergy(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreEnergy());
            info.setTotalScoreRhr(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreRhr());
            info.setTotalScoreSleep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreSleep());
            info.setTotalScoreStep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStep());
            info.setTotalScoreStress(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStress());
        }

        // 내 종합 점수 정보 불러오기
        User getMyInfo = usersService.findByUserId(userId);
        FriendRankDto myTotalScore = scoreService.getTotalScoreByUser(userId);
        myTotalScore.setUserNickname(getMyInfo.getUserNickname());
        myTotalScore.setUserId(getMyInfo.getUserId());
        myTotalScore.setUserImg(getMyInfo.getUserImg());

        // 합치기
        List<FriendRankDto> myFriendRankList = new ArrayList<FriendRankDto>();
        myFriendRankList.addAll(friendSendingRankList);
        myFriendRankList.addAll(friendReceivingRankList);
        myFriendRankList.add(myTotalScore);

        return myFriendRankList;
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
        friendRepository.save(friend.toFriendEntity());
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
