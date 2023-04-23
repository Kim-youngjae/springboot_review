package com.yj.sbbasic1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found") // 반환할 상태코드, 이유 메시지
public class DataNotFoundException extends RuntimeException {

    private static final long SerialVersionUID = 1L; //

    public DataNotFoundException(String message) {
        super(message);
    }
}
