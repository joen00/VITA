package com.ssafy.vitawearable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "total_score")
public class TotalScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long totalScoreId;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    private int year;
    private int month;
    private int day;
    private float totalScoreWeight;
    private int totalScoreEnergy;
    private int totalScoreRhr;
    private int totalScoreStress;
    private int totalScoreStep;
    private int totalScoreSleep;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createdDate;
}
