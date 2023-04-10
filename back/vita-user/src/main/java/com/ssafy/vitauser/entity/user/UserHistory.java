package com.ssafy.vitauser.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_history")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userHistoryId;
    private String userHistoryImg;
    private ZonedDateTime createdDate;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserHistory(Long userHistoryId, String userHistoryImg, User user, ZonedDateTime createdDate){
        this.userHistoryId = userHistoryId;
        this.userHistoryImg = userHistoryImg;
        this.user = user;
        this.createdDate = createdDate;
    }

}
