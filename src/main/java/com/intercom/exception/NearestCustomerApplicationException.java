package com.intercom.exception;

import lombok.AllArgsConstructor;

/**
 * Created by hadoop on 13/3/18.
 */
@AllArgsConstructor
public class NearestCustomerApplicationException extends Exception {
    String message;
    ErrorCode errorCode;
}
