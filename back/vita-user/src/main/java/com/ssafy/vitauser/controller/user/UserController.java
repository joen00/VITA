package com.ssafy.vitauser.controller.user;

import com.ssafy.vitauser.common.ApiResponse;
import com.ssafy.vitauser.dto.ExtraInfoDto;
import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.oauth.token.AuthTokenProvider;
import com.ssafy.vitauser.service.UserService;
import com.ssafy.vitauser.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthTokenProvider authTokenProvider;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    // 사용자에게 추가 정보 입력받기
    @PutMapping("/extrainfo")
    public ApiResponse extrainfo (@RequestBody ExtraInfoDto extraInfoDto, HttpServletRequest request) {
        // access token 확인
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = authTokenProvider.getUserId(accessToken);

        if (userId != null) {
            userService.putUserUpdate(extraInfoDto, userId);
        }

        return ApiResponse.success("success", "update extraInfo Success");
    }
}
