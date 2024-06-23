package com.board.boardproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {

    // 자동설정 -> jpa auditing -> 실시간으로 데이터를 넣어줌
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;     // 생성일시

    @CreatedBy
    @Column(length = 100, nullable = false, updatable = false)
    private String createdBy;            // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime updatedAt;     // 수정일시

    @LastModifiedBy
    @Column(length = 100)
    private String updatedBy;            // 수정자
}