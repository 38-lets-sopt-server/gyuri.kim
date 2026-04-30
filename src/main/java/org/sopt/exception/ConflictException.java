//409 에러
package org.sopt.exception;

public class ConflictException extends BusinessException{
    public ConflictException (ErrorCode errorCode){
        super(errorCode);
    }
}

//게시글 좋아요 중복.. 이나 닉네임 중복 검사 등 "중복"되는 상황에서 쓰일 것 같아요!