package com.ssafy.vitafriend.service;

import com.ssafy.vitafriend.dto.*;

import java.util.List;

public interface FriendService {

    List<FriendSendingListDto> getSendingFriendList(String userId);
    List<FriendReceivingListDto> getReceivingFriendList(String userId);
    List<FriendReceivingListDto> getApplyingFriendList(String userId);

    List<FriendSearchMapping> getSearchFriendList(String userId, String userNickname);

    void applyFriend(String userId, String userNickname);
    void acceptFriend(String sendingUserId, String receivingUserId);
    void rejectOrDeleteFriend(String sendingUserId, String receivingUserId);
}
