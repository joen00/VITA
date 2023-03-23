package com.ssafy.vitawearable.service;


import com.ssafy.vitawearable.dto.FriendDto;

import java.util.List;

public interface WearableFriend {

    // 친구 리스트 반환
    List<FriendDto> getFriendList(String userId);



}
