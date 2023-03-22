package com.ssafy.vitauser.entity.user;

import com.ssafy.vitauser.dto.mypage.UserUploadRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_upload")
public class UserUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userUploadId;
    private String userUploadImg;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserUpload(Long userUploadId, String userUploadImg, User user){
        this.userUploadId = userUploadId;
        this.userUploadImg = userUploadImg;
        this.user = user;
    }

    public void updateUpload(Long userUploadId, User user, String url) {
        this.userUploadId = userUploadId;
        this.userUploadImg = url;
        this.user = user;
    }

}
