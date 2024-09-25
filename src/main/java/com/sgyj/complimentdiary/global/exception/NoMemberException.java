package com.sgyj.complimentdiary.global.exception;

/**
 * 일치하는 회원이 없을 경우 에러
 */
public class NoMemberException extends RuntimeException {

    public NoMemberException(String message) {
        super(message);
    }

}
