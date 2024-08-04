package com.eazybytes.domain.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDateTime noticeBegDt;

    private LocalDateTime noticeEndDt;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Notice(
            String noticeSummary,
            String noticeDetails,
            LocalDateTime noticeBegDt,
            LocalDateTime noticeEndDt,
            LocalDateTime createDt,
            LocalDateTime updateDt) {
        this.noticeSummary = noticeSummary;
        this.noticeDetails = noticeDetails;
        this.noticeBegDt = noticeBegDt;
        this.noticeEndDt = noticeEndDt;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }

    public static Notice createNotice(
            String noticeSummary,
            String noticeDetails,
            LocalDateTime noticeBegDt,
            LocalDateTime noticeEndDt) {
        return Notice.builder()
                .noticeSummary(noticeSummary)
                .noticeDetails(noticeDetails)
                .noticeBegDt(noticeBegDt)
                .noticeEndDt(noticeEndDt)
                .createDt(LocalDateTime.now())
                .build();
    }
}