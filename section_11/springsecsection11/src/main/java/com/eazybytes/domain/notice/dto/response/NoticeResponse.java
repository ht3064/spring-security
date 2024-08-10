package com.eazybytes.domain.notice.dto.response;

import com.eazybytes.domain.notice.domain.Notice;

public record NoticeResponse(
        String noticeSummary,
        String noticeDetails) {
    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(
                notice.getNoticeSummary(),
                notice.getNoticeDetails());
    }
}