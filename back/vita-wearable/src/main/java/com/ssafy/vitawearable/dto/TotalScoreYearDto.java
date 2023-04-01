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
//    private List<xy> data;
    private List<Map<String,Integer>> data;
//    public static class xy {
//        private x x;
//        private y y;
//        public static class x {
//            private String dayString;
//        }
//        public static class y {
//            private String dayValue;
//        }
//    }

    public TotalScoreYearDto(int month) {
        this.name = month + "월";
        List<Map<String,Integer>> series = new ArrayList<>();
        for (int i = 1; i <= 31 ; i++ ) {
            Map<String,Integer> data = new HashMap<>();
            data.put("x",i);
            data.put("y",0);
            series.add(data);
        }
        this.data = series;
    }
}
