package com.eazybytes.domain.notice.domain;

import com.eazybytes.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDateTime noticeBegDt;

    private LocalDateTime noticeEndDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Notice(
            String noticeSummary, String noticeDetails,
            LocalDateTime noticeBegDt, LocalDateTime noticeEndDt) {
        this.noticeSummary = noticeSummary;
        this.noticeDetails = noticeDetails;
        this.noticeBegDt = noticeBegDt;
        this.noticeEndDt = noticeEndDt;
    }

    public static Notice createNotice(
            String noticeSummary, String noticeDetails,
            LocalDateTime noticeBegDt, LocalDateTime noticeEndDt) {
        return Notice.builder()
                .noticeSummary(noticeSummary)
                .noticeDetails(noticeDetails)
                .noticeBegDt(noticeBegDt)
                .noticeEndDt(noticeEndDt)
                .build();
    }
}
