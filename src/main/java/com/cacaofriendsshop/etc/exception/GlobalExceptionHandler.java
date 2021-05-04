package com.cacaofriendsshop.etc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<String> UserNotFoundExceptionHandle() {
        return  new ResponseEntity<>(
            "아이디 또는 비밀번호 오류", HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NoPermissionException.class)
    public final ResponseEntity<String> NoPermissionExceptionHandle() {
        return new ResponseEntity<>(
            "접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public final ResponseEntity<String> ResourceDuplicateExceptionHandle() {
        return new ResponseEntity<>(
            "리소스 중복(닉넴/이멜)", HttpStatus.CONFLICT
        );
    }

}
