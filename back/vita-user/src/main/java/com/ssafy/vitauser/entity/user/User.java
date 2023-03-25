package com.ssafy.vitauser.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.vitauser.oauth.entity.ProviderType;
import com.ssafy.vitauser.oauth.entity.RoleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(length = 100, unique = true)
    @NotNull
    @Size(max = 100)
    private String userId;

    @Column(length = 30)
    @NotNull
    @Size(max = 30)
    private String userName;

    @JsonIgnore
    @Column(length = 200)
    @NotNull
    @Size(max = 200)
    private String userPassword;

    @Column(length = 100, unique = true)
    @NotNull
    @Size(max = 100)
    private String userEmail;

    @Column(length = 500)
    @NotNull
    @Size(max = 500)
    private String userImg;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createdDate;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime updatedDate;

    private String userNickname;
    private int userAge;
    private String userGender;
    private float userHeight;
    private float userWeight;
    private int userScore;
    private String userPhoneType;
    private boolean userPublic;

    @Builder
    public User(
            @NotNull @Size(max = 100) String userId,
            @NotNull @Size(max = 30) String userName,
            @NotNull @Size(max = 200) String userPassword,
            @NotNull @Size(max = 100) String userEmail,
            @NotNull @Size(max = 500) String userImg,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull ZonedDateTime createdDate,
            @NotNull ZonedDateTime updatedDate,
            String userNickname,
            int userAge,
            String userGender,
            float userHeight,
            float userWeight,
            int userScore,
            String userPhoneType,
            boolean userPublic
    ) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = "NO_PASS";
        this.userEmail = userEmail != null ? userEmail : "NO_EMAIL";
        this.userImg = userImg != null ? userImg : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.userNickname = userNickname;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userScore = userScore;
        this.userPhoneType = userPhoneType;
        this.userPublic = userPublic;
    }





}
