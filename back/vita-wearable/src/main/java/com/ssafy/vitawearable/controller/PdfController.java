package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.service.PdfService;
import com.ssafy.vitawearable.util.HeaderUtil;
import com.ssafy.vitawearable.util.UserUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("유저 히스토리 이미지 저장 컨트롤러")
@RequestMapping("/api/wearable/savehistory")
public class PdfController {

    private final PdfService pdfService;

    private final UserUtil userUtil;

    @PostMapping
    public String saveHistoryImage(HttpServletRequest request, @RequestBody Map<String, String> req) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        String result = pdfService.saveHistoryImage(userId, req.get("image"));
        if (Objects.equals(result, "SUCCESS")) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

}
