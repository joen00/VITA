package com.ssafy.vitauser.dto;

import lombok.Data;

@Data
public class ExtraInfoDto {
    private String nickname;
    private int age;
    private String gender;
    private float height;
    private float weight;
    private String phoneType;
    private boolean userPublic;
    private String userAvatar;

    public ExtraInfoDto(String nickname, int age, String gender, float height, float weight, String phoneType, boolean userPublic, String userAvatar) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.phoneType = phoneType;
        this.userPublic = userPublic;
        this.userAvatar = userAvatar;
    }
}
