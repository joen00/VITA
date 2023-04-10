package com.ssafy.vitauser.dto.mypage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.entity.user.UserUpload;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "UserUploadRequestDto", description = "유저 zip 파일 업로드 정보")
public class UserUploadRequestDto {

    private Long userUploadId;
    private String userUploadImg;
    @JsonIgnore
    private User user;

    @Builder
    public UserUpload ToEntity() {
        return UserUpload.builder()
                .userUploadId(userUploadId)
                .userUploadImg(userUploadImg)
                .user(user)
                .build();
    }
    @Builder
    public UserUploadRequestDto(Long userUploadId, User user, String userUploadImg) {
        this.userUploadId = userUploadId;
        this.user = user;
        this.userUploadImg = userUploadImg;
    }

    public UserUploadRequestDto(User user, String userUploadImg) {
        this.user = user;
        this.userUploadImg = userUploadImg;
    }


}
