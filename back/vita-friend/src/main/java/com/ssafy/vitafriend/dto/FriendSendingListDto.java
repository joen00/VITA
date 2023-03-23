package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendSendingListDto extends UserInfoDto {

    @Builder
    public FriendSendingListDto(Friend friend) {
        this.userId = friend.getFriendReceivingUser().getUserId();
        this.userNickname = friend.getFriendReceivingUser().getUserNickname();
        this.userImg = friend.getFriendReceivingUser().getUserImg();
        this.userScore = friend.getFriendReceivingUser().getUserScore();
        this.createdDate = friend.getFriendReceivingUser().getCreatedDate();
    }
}