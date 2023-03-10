package com.ssafy.vitauser.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users_history")
public class UsersHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersHistoryId;
    private String usersHistoryImg;
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
}
