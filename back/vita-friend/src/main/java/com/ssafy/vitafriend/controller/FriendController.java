package com.ssafy.vitafriend.controller;

import com.ssafy.vitafriend.dto.FriendApplyListDto;
import com.ssafy.vitafriend.dto.FriendReceivingListDto;
import com.ssafy.vitafriend.dto.FriendSearchMapping;
import com.ssafy.vitafriend.dto.FriendSendingListDto;
import com.ssafy.vitafriend.dto.FriendRankDto;
import com.ssafy.vitafriend.dto.UserInfoDto;
import com.ssafy.vitafriend.entity.User;
import com.ssafy.vitafriend.service.FriendService;
import com.ssafy.vitafriend.service.UsersService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/friend")
@CrossOrigin(origins = "http://localhost:8081")
public class FriendController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UsersService usersService;

    // 테스트
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    // 친구 목록 GET
    @GetMapping
    public ResponseEntity<?> friendList(@RequestHeader HttpHeaders headers) {
        try {
            // 친구를 담을 리스트를 <친구리스트 부모 클래스 형>으로 선언
            List<UserInfoDto> myFriendList = new ArrayList<>();

            // header에서 받은 userId에 해당하는 유저의 받은 친구, 보낸 친구를 각각 리스트에 담기
            List<?> ReceivingFriendList = friendService.getReceivingFriendList(headers.getFirst("userID"));
            List<?> SendingFriendList = friendService.getSendingFriendList(headers.getFirst("userID"));
            for(Object
                    friend : SendingFriendList) {
                myFriendList.add((UserInfoDto) friend);
            }
            for(Object
                    friend : ReceivingFriendList) {
                myFriendList.add((UserInfoDto) friend);
            }
            if (myFriendList != null && !myFriendList.isEmpty()) {
                return ResponseEntity.ok().body(myFriendList);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    // 친구요청 수락하기 PUT
    @PutMapping
    public void acceptFriend(@RequestHeader HttpHeaders suId, @RequestHeader HttpHeaders ruId) throws Exception {
        friendService.acceptFriend(suId.getFirst("sendingUserId"), ruId.getFirst("receivingUserId"));
    }

    // 친구요청 거절 및 친구 삭제 DELETE
    @DeleteMapping
    public void rejectOrDeleteFriend(@RequestHeader HttpHeaders suId, @RequestHeader HttpHeaders ruId) throws Exception {
        friendService.rejectOrDeleteFriend(suId.getFirst("sendingUserId"), ruId.getFirst("receivingUserId"));
    }

    // 친구 신청온 리스트 GET
    @GetMapping("/applyList")
    public ResponseEntity<?> applyingFriendList(@RequestHeader HttpHeaders headers) {
        try {
            List<FriendReceivingListDto> applyingFriendList = friendService.getApplyingFriendList(headers.getFirst("userID"));
            System.out.println("asdasd");
            if (applyingFriendList != null && !applyingFriendList.isEmpty()) {
                return new ResponseEntity<List<FriendApplyListDto>>(applyingFriendList.stream().map(friend -> modelMapper.map(friend, FriendApplyListDto.class)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
   
    // 친구 신청 검색(친구로 등록이 안되어 있는 유저만 모두 보여주기) GET
    @GetMapping("/apply/{nickname}")
    public ResponseEntity<?> searchFriendList(@RequestHeader HttpHeaders headers, @PathVariable("nickname") String nickname) {
        try {

            String getUserId = headers.getFirst("userID");
            List<FriendSearchMapping> usersList = friendService.getSearchFriendList(getUserId, nickname);

            // 목록에서 자신을 제외
            usersList.removeIf(users -> users.getUser_Id().equals(getUserId));

            // 이미 친구로 등록된 사람들 제외
            usersList.removeIf(users -> Objects.equals(users.getFriend_status(), "accepted"));

            if (usersList != null && !usersList.isEmpty()) {
                return ResponseEntity.ok().body(usersList);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }

    //
    @PostMapping("/apply")
    public void applyFriend(@RequestHeader HttpHeaders headers, @RequestBody Map<String, String> req) throws Exception {
        System.out.println(req);
        friendService.applyFriend(headers.getFirst("userID"), req.get("user_nickname"));
    }

    // 친구 랭킹 GET
    @GetMapping("/rank")
    public ResponseEntity<?> rankFriendList(@RequestHeader HttpHeaders headers) {
        try {
            // 친구를 담을 리스트를 <친구리스트 부모 클래스 형>으로 선언
            User myInfo = usersService.findByUserId(headers.getFirst("userID"));

            // 랭킹에 나도 포함 시킴, modelMapper로 타입 맞춰서 넣기
            List<UserInfoDto> rankFriendList = new ArrayList<>();
            rankFriendList.add(modelMapper.map(myInfo, UserInfoDto.class));

            // header에서 받은 userId에 해당하는 유저의 받은 친구, 보낸 친구를 각각 리스트에 담기
            List<FriendReceivingListDto> ReceivingFriendList = friendService.getReceivingFriendList(headers.getFirst("userID"));
            List<FriendSendingListDto> SendingFriendList = friendService.getSendingFriendList(headers.getFirst("userID"));
            for(Object
                    friend : SendingFriendList) {
                rankFriendList.add((UserInfoDto) friend);
            }
            for(Object
                    friend : ReceivingFriendList) {
                rankFriendList.add((UserInfoDto) friend);
            }

            // 최종 출력
            if (rankFriendList != null && !rankFriendList.isEmpty()) {
                return new ResponseEntity<List<?>>(rankFriendList.stream().map(friend -> modelMapper.map(friend, FriendRankDto.class)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<?> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
