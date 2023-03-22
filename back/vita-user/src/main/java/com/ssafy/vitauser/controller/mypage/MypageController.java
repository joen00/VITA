package com.ssafy.vitauser.controller.mypage;

import com.ssafy.vitauser.dto.mypage.UserBadgeResponseDto;
import com.ssafy.vitauser.service.mypage.AwsS3Service;
import com.ssafy.vitauser.service.mypage.MypageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("마이페이지 API")
@RequestMapping(path="/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final AwsS3Service awsS3Service;


    @ApiOperation(value = "유저 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> selectOne(@ApiParam(value = "user_id", required = true) @PathVariable("userId") String id){
        return new ResponseEntity(mypageService.selectOne(id), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 뱃지 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping("/user/badge/{userId}")
    public ResponseEntity<?> selectAllBadge(@ApiParam(value = "user_id", required = true) @PathVariable("userId") String id){
        return new ResponseEntity(mypageService.selectAllBadge(id), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 히스토리 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping("/user/history/{userId}")
    public ResponseEntity<?> selectAllHistory(@ApiParam(value = "user_id", required = true) @PathVariable("userId") String id){
        return new ResponseEntity(mypageService.selectAllHistory(id), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 파일 업로드 저장", notes = "성공하면 success.", response = String.class)
    @PostMapping("/user/upload/{userId}")
    public ResponseEntity<String> uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile, @PathVariable String userId) throws Exception {
        // 파일 저장함
        String url = awsS3Service.uploadFileV1(multipartFile);
        // url update 해줌
        mypageService.updateUpload(userId, url);
        return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
    }


}
