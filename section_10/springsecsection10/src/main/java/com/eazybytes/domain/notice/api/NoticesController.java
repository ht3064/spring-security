package com.eazybytes.domain.notice.api;

import com.eazybytes.domain.notice.application.NoticeService;
import com.eazybytes.domain.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponse>> getNotices() {
        return noticeService.getNotices();
    }
}
