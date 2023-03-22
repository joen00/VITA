package com.ssafy.vitafriend.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "friend")
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;
    private String friendStatus;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_sending_user_id")
    private Users friendSendingUser;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_receiving_user_id")
    private Users friendReceivingUser;

    public void acceptFriendRelation() {
        this.friendStatus = "accepted";
    }
}
