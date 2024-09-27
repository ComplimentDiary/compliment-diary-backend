package com.sgyj.complimentdiary.global.exceptions;

/**
 * 패스워드 불일치 에러
 */
public class NoMatchPasswordException extends RuntimeException {

    public NoMatchPasswordException(String message) {
        super(message);
    }

}
