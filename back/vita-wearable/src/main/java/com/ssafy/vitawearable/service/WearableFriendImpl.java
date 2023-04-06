package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.FriendDto;
import com.ssafy.vitawearable.entity.Friend;
import com.ssafy.vitawearable.repo.FriendRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WearableFriendImpl implements WearableFriend {
    private final FriendRepo friendRepo;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    // 친구 리스트 반환
    public List<FriendDto> getFriendList(String userId) {
        List<FriendDto> list1 = friendRepo.findByFriendSendingUser_UserId(userId)
                .stream()
                .filter(u -> u.getFriendStatus().equals("accepted"))
                .map(Friend::getFriendReceivingUser)
                .map(u -> mapper.map(u,FriendDto.class))
                .collect(Collectors.toList());
        List<FriendDto> list2 = friendRepo.findByFriendReceivingUser_UserId(userId)
                .stream()
                .filter(u -> u.getFriendStatus().equals("accepted"))
                .map(Friend::getFriendSendingUser)
                .map(u -> mapper.map(u,FriendDto.class))
                .collect(Collectors.toList());
        list1.addAll(list2);
        return list1;
    }
}
