//PostService에서 예외 던지고
//PostNotFoundException 발생
//GlobalExceptionHandler가 잡아서 HTTP 응답으로 변환

//ErrorCode에서 상태코드 + 메시지 보관한다.

package org.sopt.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
    //캡슐화를 위해 final로 지정

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    //getter 추가
    public String getMessage(){
        return message;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}