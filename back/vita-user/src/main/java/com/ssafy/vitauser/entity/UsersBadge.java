package com.ssafy.vitauser.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users_badge")
public class UsersBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersBadgeId;
    private boolean usersBadgeGet;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private Badge badge;

}
