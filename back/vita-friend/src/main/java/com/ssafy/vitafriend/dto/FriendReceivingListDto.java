package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FriendReceivingListDto extends UserInfoDto {

    @Builder
    public FriendReceivingListDto(Friend friend) {
        this.userId = friend.getFriendSendingUser().getUserId();
        this.userNickname = friend.getFriendSendingUser().getUserNickname();
        this.userImg = friend.getFriendSendingUser().getUserImg();
        this.userScore = friend.getFriendReceivingUser().getUserScore();
        this.createdDate = friend.getFriendSendingUser().getCreatedDate();
    }
}
