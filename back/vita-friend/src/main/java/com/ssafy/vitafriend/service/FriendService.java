package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;

import java.util.List;

public interface FriendService {
    
    // 친구 관계 확인
    List<FriendSendingListDto> getSendingFriendList(String userId);
    List<FriendReceivingListDto> getReceivingFriendList(String userId);
    // 친구 관계 확인 (닉네임으로 검색)
    List<FriendSendingListDto> getSendingFriendListLike(String userId, String nickname);
    List<FriendReceivingListDto> getReceivingFriendListLike(String userId, String nickname);
    
    // 친구 요청 전송된 리스트
    List<FriendReceivingListDto> getApplyingFriendList(String userId);
    
    // 친구 검색
    List<FriendSearchMapping> getSearchFriendList(String userId);
    // 친구 검색 (닉네임으로 검색)
    List<FriendSearchMapping> getSearchFriendListLike(String userId, String userNickname);

    // 랭킹 점수
    List<?> getFriendRankList(String userId, String scoreType);
    List<FriendStepRankDto> getFriendStepRankLIst(String userId);
    
    // 친구 요청, 수락, 거절 및 삭제
    String applyFriend(String userId, String userNickname);
    String acceptFriend(String sendingUserId, String receivingUserId);
    String rejectOrDeleteFriend(String sendingUserId, String receivingUserId);
}
