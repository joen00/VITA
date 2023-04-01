package com.ssafy.vitawearable.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_upload")
public class UserUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userUploadId;
    private String userUploadImg;
    private ZonedDateTime createdDate;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
