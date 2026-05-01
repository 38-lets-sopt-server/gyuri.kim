//PostService에서 예외 던지고
//PostNotFoundException 발생
//GlobalExceptionHandler가 잡아서 HTTP 응답으로 변환

//ErrorCode에서 상태코드 + 메시지 보관한다.

package org.sopt.exception;

import org.springframework.http.HttpStatus;
public enum ErrorCode {
    //400 BadRequest
    INVALID_INPUT_VALUE("COMMON_001", HttpStatus.BAD_REQUEST, "잘못된 입려값입니다."),
    TITLE_TOO_LONG("POST_002", HttpStatus.BAD_REQUEST, "게시글 제목은 50자를 초과할 수 없습니다."),
    CONTENT_REQUIRED("POST_003", HttpStatus.BAD_REQUEST, "게시글 내용은 필수입니다."),

    //404 Not Found
    POST_NOT_FOUND("POST_001", HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다"),
    USER_NOT_FOUND("USER_001", HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"),
    LIKE_NOT_FOUND("LIKE_002", HttpStatus.NOT_FOUND, "좋아요를 누르지 않았습니다"),
    //409 Conflict -> 이 두개는 ConflictException을 쓰는 곳이 없어서, 써볼려고 한번 작성해봤어요!
    //기능 구현도 추후에 해놓겠습니다!
    SAME_NICKNAME("USER_002", HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다."),
    LIKE_ALREADY_EXISTS("LIKE_001", HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
    //getter 추가
    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}