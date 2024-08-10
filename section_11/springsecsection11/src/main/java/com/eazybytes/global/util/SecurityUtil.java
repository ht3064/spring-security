package com.eazybytes.global.util;

import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public String getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return authentication.getName();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.AUTH_NOT_FOUND);
        }
    }
}