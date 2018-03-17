package com.intercom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum ErrorCode {

    UNABLE_TO_READ_FILE(1),
    UNABLE_TO_PARSE_FILE(2),
    NO_ELLEMENT_REMAINING_IN_FILE(3),
    ADDING_NULL_VALUE(4);
    @Getter
    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

