package com.ssafy.vitafriend.entity;

import io.swagger.annotations.ApiParam;
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
    @ApiParam(value = "친구의 상태. 이미 친구로 등록된 상태이면 'accepted', 친구 신청이 보내진 상태이면 'applied'")
    private String friendStatus;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_sending_user_id")
    private User friendSendingUser;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_receiving_user_id")
    private User friendReceivingUser;

    public void acceptFriendRelation() {
        this.friendStatus = "accepted";
    }
}
