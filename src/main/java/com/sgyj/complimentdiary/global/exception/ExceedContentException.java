package com.sgyj.complimentdiary.global.exception;

/**
 * 콘텐츠 등록 허용 갯수가 초과된 경우 에러
 */
public class ExceedContentException extends RuntimeException {

    public ExceedContentException(String message) {
        super(message);
    }

}
