package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 친구 리스트 userId와 닉네임 데이터 Dto
public class FriendDto {
    private String userId;
    private String userNickname;
}
