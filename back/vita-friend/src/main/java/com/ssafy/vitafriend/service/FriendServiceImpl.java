package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;
import com.ssafy.vitafriend.dto.FriendReceivingListDto;
import com.ssafy.vitafriend.dto.FriendSendingListDto;
import com.ssafy.vitafriend.entity.Friend;
import com.ssafy.vitafriend.entity.Score;
import com.ssafy.vitafriend.entity.User;
import com.ssafy.vitafriend.repository.FriendRepository;
import com.ssafy.vitafriend.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    public List<?> getFriendRankList(String userId, String scoreType) {

        List<FriendTotalRankDto> myFriendRankList = new ArrayList<>();
        // 친구 신청 보내서 친구가 된 사람들 목록
        List<FriendSendingListDto> getFriendSendingList = friendRepository.findByFriendSendingUser_userIdAndFriendStatus(userId, "accepted");
        // 친구 신청 받아서 친구가 된 사람들 목록
        List<FriendReceivingListDto> getFriendRecevingList = friendRepository.findByFriendReceivingUser_userIdAndFriendStatus(userId, "accepted");

        // 내 종합점수 불러오기
        User getMyInfo = usersService.findByUserId(userId);

        // 결과로 리턴받을 리스트
        List<FriendTotalRankDto> sendingFriendTotalScoreList = getFriendSendingList.stream().map(friend -> modelMapper.map(friend, FriendTotalRankDto.class)).collect(Collectors.toList());
        List<FriendTotalRankDto> receivingFriendTotalScoreList = getFriendRecevingList.stream().map(friend -> modelMapper.map(friend, FriendTotalRankDto.class)).collect(Collectors.toList());
        for (FriendTotalRankDto info : sendingFriendTotalScoreList) {
            info.setTotalScore(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScore());
            info.setTotalScoreEnergy(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreEnergy());
            info.setTotalScoreRhr(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreRhr());
            info.setTotalScoreSleep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreSleep());
            info.setTotalScoreStep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStep());
            info.setTotalScoreStress(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStress());
        }
        // 친구 신청 받아서 친구가 된 사람들 의 점수 정보 불러오기
        for (FriendTotalRankDto info : receivingFriendTotalScoreList) {
            info.setTotalScore(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScore());
            info.setTotalScoreEnergy(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreEnergy());
            info.setTotalScoreRhr(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreRhr());
            info.setTotalScoreSleep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreSleep());
            info.setTotalScoreStep(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStep());
            info.setTotalScoreStress(scoreService.getTotalScoreByUser(info.getUserId()).getTotalScoreStress());
        }
        myFriendRankList.addAll(sendingFriendTotalScoreList);
        myFriendRankList.addAll(receivingFriendTotalScoreList);

        FriendTotalRankDto myTotalScore = modelMapper.map(scoreService.getTotalScoreByUser(userId), FriendTotalRankDto.class);
        myTotalScore.setUserNickname(getMyInfo.getUserNickname());
        myTotalScore.setUserId(getMyInfo.getUserId());
        myTotalScore.setUserImg(getMyInfo.getUserImg());
        myFriendRankList.add(myTotalScore);

        // 친구 신청 보내서 친구가 된 사람들 의 점수 정보 불러오기
        switch (scoreType) {
            case "total" :
                myFriendRankList.sort(Comparator.comparing(FriendTotalRankDto::getTotalScore).reversed());
                return myFriendRankList;

            case "step" :
                List<FriendStepRankDto> tmp1 = myFriendRankList.stream().map(friend -> modelMapper.map(friend, FriendStepRankDto.class)).collect(Collectors.toList());
                tmp1.sort(Comparator.comparing(FriendStepRankDto::getTotalScoreStep).reversed());
                return tmp1;

            case "energy" :
                List<FriendEnergyRankDto> tmp2 = myFriendRankList.stream().map(friend -> modelMapper.map(friend, FriendEnergyRankDto.class)).collect(Collectors.toList());
                tmp2.sort(Comparator.comparing(FriendEnergyRankDto::getTotalScoreEnergy).reversed());
                return tmp2;

            case "rhr" :
                List<FriendRhrRankDto> tmp3 = myFriendRankList.stream().map(friend -> modelMapper.map(friend, FriendRhrRankDto.class)).collect(Collectors.toList());
                tmp3.sort(Comparator.comparing(FriendRhrRankDto::getTotalScoreRhr).reversed());
                return tmp3;

            case "stress" :
                List<FriendStressRankDto> tmp4 = myFriendRankList.stream().map(friend -> modelMapper.map(friend, FriendStressRankDto.class)).collect(Collectors.toList());
                tmp4.sort(Comparator.comparing(FriendStressRankDto::getTotalScoreStress).reversed());
                return tmp4;

            case "sleep" :
                List<FriendSleepRankDto> tmp5 = myFriendRankList.stream().map(friend -> modelMapper.map(friend, FriendSleepRankDto.class)).collect(Collectors.toList());
                tmp5.sort(Comparator.comparing(FriendSleepRankDto::getTotalScoreSleep).reversed());
                return tmp5;

        }

        // 내 종합 점수 정보 불러오기
        return myFriendRankList;


    }

    @Override
    public List<FriendStepRankDto> getFriendStepRankLIst(String userId) {
        return null;
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
    public String applyFriend(String userId, String userNickname) {
        // userId = 내 아이디, userNickname = 신청 보낼 유저의 닉네임
        Friend applySendInfo = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userNickname(userId, userNickname);
        Friend applyReceiveInfo = friendRepository.findByFriendSendingUser_userNicknameAndFriendReceivingUser_userId(userNickname, userId);
//        System.out.println(applySendInfo.getFriendId());
//        System.out.println(applyReceiveInfo.getFriendId());

        FriendDto friend = new FriendDto();
        if (applySendInfo == null && applyReceiveInfo == null) {
            friend.setFriendSendingUser(usersRepository.findByUserId(userId));
            friend.setFriendReceivingUser(usersRepository.findByUserNickname(userNickname));
            friend.setFriendStatus("applied");
            friendRepository.save(friend.toFriendEntity());
            return "success";
        } else {
            return "fail";
        }

    }

    @Override
    public String acceptFriend(String sendingUserId, String receivingUserId) {
        Friend currentRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userIdAndFriendStatus(sendingUserId, receivingUserId, "applied");
        currentRelation.acceptFriendRelation();
        if (currentRelation == null) {
            return "fail";
        }
        friendRepository.save(currentRelation);
        return "success";
    }

    @Override
    public String rejectOrDeleteFriend(String sendingUserId, String receivingUserId) {
        Friend rejectOrDeleteRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userId(sendingUserId, receivingUserId);
        Friend rejectOrDeleteReverseRelation = friendRepository.findByFriendSendingUser_userIdAndFriendReceivingUser_userId(receivingUserId, sendingUserId);
        if (rejectOrDeleteRelation != null) {
            friendRepository.deleteById(rejectOrDeleteRelation.getFriendId());
            return "success";
        } else if (rejectOrDeleteReverseRelation != null) {
            friendRepository.deleteById(rejectOrDeleteReverseRelation.getFriendId());
            return "success";
        }
        return "fail";
    }

}
