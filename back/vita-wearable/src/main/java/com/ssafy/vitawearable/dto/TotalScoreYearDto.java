package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        this.data = new ArrayList<>();
    }
}
