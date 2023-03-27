package com.ssafy.vitauser.controller.mypage;

import com.ssafy.vitauser.dto.mypage.UserBadgeResponseDto;
import com.ssafy.vitauser.oauth.token.AuthTokenProvider;
import com.ssafy.vitauser.service.mypage.AwsS3Service;
import com.ssafy.vitauser.service.mypage.MypageService;
import com.ssafy.vitauser.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("마이페이지 API")
@RequestMapping(path="/users/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final AwsS3Service awsS3Service;
    private final AuthTokenProvider authTokenProvider;

    @ApiOperation(value = "유저 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping
    public ResponseEntity<?> selectOne(HttpServletRequest request){
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = authTokenProvider.getUserId(accessToken);

        return new ResponseEntity(mypageService.selectOne(userId), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 뱃지 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping("/badge")
    public ResponseEntity<?> selectAllBadge(HttpServletRequest request){
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = authTokenProvider.getUserId(accessToken);

        return new ResponseEntity(mypageService.selectAllBadge(userId), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 히스토리 정보 조회", notes = "성공하면 success.", response = String.class)
    @GetMapping("/history")
    public ResponseEntity<?> selectAllHistory(HttpServletRequest request){
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = authTokenProvider.getUserId(accessToken);

        return new ResponseEntity(mypageService.selectAllHistory(userId), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 파일 업로드 저장", notes = "성공하면 success.", response = String.class)
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = authTokenProvider.getUserId(accessToken);

        // 파일 저장함
        String url = awsS3Service.uploadFileV1(multipartFile);
        // url update 해줌
        mypageService.updateUpload(userId, url);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.ACCEPTED);
    }


}
