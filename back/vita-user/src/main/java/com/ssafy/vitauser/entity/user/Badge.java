package com.ssafy.vitauser.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;
    private String badgeName;

    @Builder
    public Badge(Long badgeId, String badgeName){
        this.badgeId = badgeId;
        this.badgeName = badgeName;
    }

}
