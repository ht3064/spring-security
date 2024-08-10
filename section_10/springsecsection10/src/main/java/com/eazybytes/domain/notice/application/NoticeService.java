package com.eazybytes.domain.notice.application;

import com.eazybytes.domain.notice.dao.NoticeRepository;
import com.eazybytes.domain.notice.domain.Notice;
import com.eazybytes.domain.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public ResponseEntity<List<NoticeResponse>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(
                        notices.stream()
                                .map(NoticeResponse::from)
                                .toList());
    }
}
