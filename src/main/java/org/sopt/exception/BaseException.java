//커스텀 예외를 던진다.
//message에 "에러내용~" 저장된다
//errorCode: "에러코드~" 저장된다
//GlobalExceptionHandler가 e.getMessage를 통해 에너래용~을 꺼낸다.
//에러코드~도 꺼낸다 -> 404 같은 httpstatus 반환
//클라한테 404 + 에러내용~ 전달한다.

package org.sopt.exception;
public class BaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        }
    public ErrorCode getErrorCode(){
        return errorCode;
    } //객체 초기화
}

/*Super(errorCode.getMessage()) 에서.... RuntimeException에 메시지 보내기 -> e.getMessage로 에러 메시지를 꺼낼 때 이 정보를 사용한다.*/
/*필드에 저장하기 -> e.getErrorCode().getHttpStatus()로 HTTP 상태코드를 꺼낼 때 이 정보를 사용한다.*/
/**/