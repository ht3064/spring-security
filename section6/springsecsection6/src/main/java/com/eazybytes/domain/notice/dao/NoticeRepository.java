package com.eazybytes.domain.notice.dao;

import com.eazybytes.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query("from Notice n where n.createdDt between n.noticeBegDt AND n.noticeEndDt")
    List<Notice> findAllActiveNotices();
}