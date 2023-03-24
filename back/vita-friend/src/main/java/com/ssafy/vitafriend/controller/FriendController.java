package com.ssafy.vitafriend.controller;

import com.ssafy.vitafriend.dto.*;
import com.ssafy.vitafriend.service.FriendService;
import com.ssafy.vitafriend.service.ScoreService;
import com.ssafy.vitafriend.service.UsersService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/friend")
@Api(value = "비타프렌즈 API")
public class FriendController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ScoreService scoreService;

    // 테스트
    @GetMapping("/hello")
    @ApiOperation(value = "테스트", notes = "테스트")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    // 친구 목록 GET
    @GetMapping
    @ApiOperation(value = "친구목록 조회", notes = "자신이 가지고 있는 친구목록을 조회합니다.")
    @ApiImplicitParam(name = "userID", value = "자신의 userID", dataType = "String", paramType = "header", example = "1")
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

    // 친구 요청 수락 POST
    @PostMapping("/apply")
    @ApiOperation(value = "친구 신청 보내기", notes = "친구 신청을 보냅니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "자신의 userID", dataType = "String", paramType = "header", example = "1"),
            @ApiImplicitParam(name = "user_nickname", value = "친구 신청을 보낼 사용자의 닉네임", dataType = "String", paramType = "body", example = "홍길동")
    })
    public String applyFriend(@RequestHeader HttpHeaders headers, @ApiIgnore @RequestBody Map<String, String> req) throws Exception {
        System.out.println(req);
        String result = friendService.applyFriend(headers.getFirst("userID"), req.get("user_nickname"));
        if (Objects.equals(result, "success")) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 친구요청 수락하기 PUT
    @PutMapping
    @ApiOperation(value = "친구요청 수락", notes = "친구요청을 수락합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendingUserId", value = "자신의 userID", dataType = "String", paramType = "header", example = "1"),
            @ApiImplicitParam(name = "receivingUserId", value = "수락할 사람의 userID", dataType = "String", paramType = "header", example = "2")
    })
    public String acceptFriend(@RequestHeader HttpHeaders suId, @RequestHeader HttpHeaders ruId) throws Exception {
        String result = friendService.acceptFriend(suId.getFirst("sendingUserId"), ruId.getFirst("receivingUserId"));
        if (Objects.equals(result, "success")) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 친구요청 거절 및 친구 삭제 DELETE
    @DeleteMapping
    @ApiOperation(value = "친구요청 거절 및 친구삭제", notes = "친구요청을 거절하거나 친구목록에서 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendingUserId", value = "자신의 userID", dataType = "String", paramType = "header", example = "1"),
            @ApiImplicitParam(name = "receivingUserId", value = "요청을 받을 사람의 userID", dataType = "String", paramType = "header", example = "2")
    })
    public String rejectOrDeleteFriend(@RequestHeader HttpHeaders suId, @RequestHeader HttpHeaders ruId) throws Exception {
        String result = friendService.rejectOrDeleteFriend(suId.getFirst("sendingUserId"), ruId.getFirst("receivingUserId"));
        if (Objects.equals(result, "success")) {
            return "success";
        } else {
            return "fail";
        }
    }

    // 친구 신청온 리스트 GET
    @ApiOperation(value = "친구 신청 받은 리스트", notes = "친구 신청을 받은 목록을 조회합니다. 프론트에서 수락, 거절 버튼이 뜨는 목록에 해당")
    @GetMapping("/applyList")
    @ApiImplicitParam(name = "userID", value = "자신의 userID", dataType = "String", paramType = "header", example = "1")
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
    @ApiOperation(value = "친구 검색", notes = "아직 친구로 등록되지 않은 사용자 목록을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "자신의 userID", dataType = "String", paramType = "header", example = "1"),
            @ApiImplicitParam(name = "nickname", value = "검색할 닉네임", dataType = "String", paramType = "path", example = "홍길동")
    })
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

    // 친구 랭킹 GET
    @GetMapping("/rank/{scoreType}")
    @ApiOperation(value = "종합점수 조회", notes = "친구들의 종합점수 목록을 조회합니다.")
    @ApiImplicitParam(name = "userID", value = "자신의 userID", dataType = "String", paramType = "header", example = "1")
    public ResponseEntity<?> rankFriendList(@RequestHeader HttpHeaders headers, @PathVariable("scoreType") String scoreType) {
        try {
            // 출력시킬 종합 점수 리스트
            List<FriendTotalRankDto> rankFriendList = new ArrayList<>();
            
            // 친구들 정보를 받아와서
            List<?> friendRankList = friendService.getFriendRankList(headers.getFirst("userID"), scoreType);
//            // 종합 점수 리스트에 저장
//            rankFriendList.addAll(friendRankList);
//            // 종합 점수를 내림차순으로 정렬
//            rankFriendList.sort(Comparator.comparing(FriendRankDto::getTotalScore).reversed());
            // 최종 출력
            if (friendRankList != null && !friendRankList.isEmpty()) {
                return new ResponseEntity<List<?>>(friendRankList, HttpStatus.OK);
//                return new ResponseEntity<List<?>>(rankFriendList, HttpStatus.OK);
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
