package com.eazybytes.domain.notice.dto.response;

import com.eazybytes.domain.notice.domain.Notice;

import java.time.LocalDateTime;

public record NoticeResponse(
        String noticeSummary,
        String noticeDetails,
        LocalDateTime noticeBegDt,
        LocalDateTime noticeEndDt,
        LocalDateTime createdDt) {
    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(
                notice.getNoticeSummary(),
                notice.getNoticeDetails(),
                notice.getNoticeBegDt(),
                notice.getNoticeEndDt(),
                notice.getCreatedDt());
    }
}