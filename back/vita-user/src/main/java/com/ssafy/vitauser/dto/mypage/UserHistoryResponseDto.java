package com.ssafy.vitauser.dto.mypage;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@ApiModel(value = "UserHistoryResponseDto : 유저 히스토리 정보", description = "유저 히스토리 정보를 나타낸다.")
public class UserHistoryResponseDto {
    private String userHistoryImg;
    private ZonedDateTime createdDate;
    @Builder
    public UserHistoryResponseDto(String userHistoryImg, ZonedDateTime createdDate){
        this.userHistoryImg = userHistoryImg;
        this.createdDate = createdDate;
    }

}
