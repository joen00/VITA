package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;

import java.util.List;

public interface FriendService {
    
    // 친구 관계 확인
    List<FriendSendingListDto> getSendingFriendList(String userId);
    List<FriendReceivingListDto> getReceivingFriendList(String userId);
    
    // 친구 요청 전송된 리스트
    List<FriendReceivingListDto> getApplyingFriendList(String userId);
    
    // 친구 검색
    List<FriendSearchMapping> getSearchFriendList(String userId, String userNickname);
    
    // 랭킹 점수
    List<FriendRankDto> getFriendRankList(String userId);
    
    // 친구 요청, 수락, 거절 및 삭제
    void applyFriend(String userId, String userNickname);
    void acceptFriend(String sendingUserId, String receivingUserId);
    void rejectOrDeleteFriend(String sendingUserId, String receivingUserId);
}
