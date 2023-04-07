package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.pdf.PdfDto;

public interface PdfService {
    String saveHistoryImage(String userId, String userHistoryImage);
}
