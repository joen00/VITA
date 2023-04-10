package com.ssafy.vitawearable.dto.pdf;

import com.ssafy.vitawearable.entity.User;
import com.ssafy.vitawearable.entity.UserHistory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PdfDto {
    private User user;
    @Lob
    private String userHistoryImg;
    private ZonedDateTime createdDate;
    public UserHistory toUserHistoryEntity() {
        return UserHistory.builder()
                .user(user)
                .userHistoryImg(userHistoryImg)
                .createdDate(createdDate)
                .build();
    }
}
