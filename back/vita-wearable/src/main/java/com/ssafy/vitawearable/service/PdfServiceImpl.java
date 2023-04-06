package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.pdf.PdfDto;
import com.ssafy.vitawearable.repo.UserHistoryRepo;
import com.ssafy.vitawearable.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class PdfServiceImpl implements PdfService {

    private final UserHistoryRepo userHistoryRepo;
    private final UserRepo userRepo;

    @Override
    public String saveHistoryImage(String userId, String userHistoryImage) {
        PdfDto historyInfo = new PdfDto();
        try {
            historyInfo.setUser(userRepo.findUserByUserId(userId));
            historyInfo.setUserHistoryImg(userHistoryImage);
            historyInfo.setCreatedDate(ZonedDateTime.now());
            userHistoryRepo.save(historyInfo.toUserHistoryEntity());
            return "SUCCESS";
        } catch (Exception e) {
            return "FAIL";
        }

    }
}
