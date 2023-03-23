package com.ssafy.vitafriend.dto;

import com.ssafy.vitafriend.entity.Friend;
import com.ssafy.vitafriend.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FriendDto extends UserInfoDto{

    private Long friendId;
    private String friendStatus;
    private User friendSendingUser;
    private User friendReceivingUser;

    // Dto를 이용하여 Entity 객체를 생성하는 방법. Entity에 Setter가 없기 때문에 이렇게 사용
    public Friend toEntity() {
        return Friend.builder()
                .friendStatus(friendStatus)
                .friendSendingUser(friendSendingUser)
                .friendReceivingUser(friendReceivingUser)
                .build();
    }
}
