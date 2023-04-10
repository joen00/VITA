package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TotalScoreYearDto {
    private String name;
    private List<xy> data;

    public TotalScoreYearDto(int month) {
        this.name = month + "월";
        List<xy> series = new ArrayList<>();
        for (int i = 1; i <= 31 ; i++ ) {
            xy data = new xy();
            data.setX(String.valueOf(i));
            data.setY(0);
            series.add(data);
        }
        this.data = series;
    }

    @Getter
    @Setter
    public static class xy {
        private String x;
        private int y;
    }
}
