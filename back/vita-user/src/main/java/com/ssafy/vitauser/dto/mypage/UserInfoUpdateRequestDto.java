package com.ssafy.vitauser.dto.mypage;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "UserInfoUpdateRequestDto : 유저 정보 수정", description = "유저 정보 수정을 위한 Dto")
public class UserInfoUpdateRequestDto {
    private int userAge;
    private float userHeight;
    private float userWeight;
    private String userPhoneType;

    @Builder
    public UserInfoUpdateRequestDto(int userAge, float userHeight, float userWeight, String userPhoneType) {
        this.userAge = userAge;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userPhoneType = userPhoneType;
    }
}
