package com.ssafy.vitauser.dto.mypage;


import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "UserResponseDto : 유저 정보", description = "유저 정보를 나타낸다.")
public class UserInfoResponseDto {

    private String userId;
    private String userName;
    private String userImg;
    private String userNickname;
    private int userAge;
    private String userGender;
    private float userHeight;
    private float userWeight;
    private String userPhoneType;
    private boolean userPublic;
    private int userScore;

    @Builder
    public UserInfoResponseDto(String userId, String userName, String userImg, String userNickname, int userAge,
                               String userGender, float userHeight, float userWeight, String userPhoneType,
                               boolean userPublic, int userScore ) {
        this.userId = userId;
        this.userName = userName;
        this.userImg = userImg;
        this.userNickname= userNickname;
        this.userAge=userAge;
        this.userGender=userGender;
        this.userHeight=userHeight;
        this.userWeight=userWeight;
        this.userPhoneType=userPhoneType;
        this.userPublic=userPublic;
        this.userScore=userScore;
    }

}
