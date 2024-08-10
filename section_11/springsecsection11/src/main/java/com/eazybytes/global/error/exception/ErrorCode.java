package com.eazybytes.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원의 계좌 정보를 찾을 수 없습니다."),

    AUTH_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "시큐리티 인증 정보를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
