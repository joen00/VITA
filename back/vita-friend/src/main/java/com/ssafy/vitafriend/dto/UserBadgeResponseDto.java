package com.ssafy.vitafriend.dto;


import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "UserBadgeResponseDto : 유저 뱃지 정보", description = "유저 뱃지 정보를 나타낸다.")
public class UserBadgeResponseDto {

    private Long userBadgeId;
    private Long badgeId;
    private boolean userBadgeGet;

    @Builder
    public UserBadgeResponseDto(Long userBadgeId, boolean userBadgeGet, Long badgeId){
        this.userBadgeId = userBadgeId;
        this.badgeId = badgeId;
        this.userBadgeGet = userBadgeGet;
    }

}
