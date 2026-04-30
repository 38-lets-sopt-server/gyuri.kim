package org.sopt.exception;
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        }
    public ErrorCode getErrorCode(){
        return errorCode;
    } //객체 초기화
}

//커스텀 예외를 던진다.
//message에 "에러내용~" 저장된다
//errorCode: "에러코드~" 저장된다
//GlobalExceptionHandler가 e.getMessage를 통해 에너래용~을 꺼낸다.
//에러코드~도 꺼낸다 -> 404 같은 httpstatus 반환
//클라한테 404 + 에러내용~ 전달한다.

/*Super(errorCode.getMessage()) 에서.... RuntimeException에 메시지 보내기 -> e.getMessage로 에러 메시지를 꺼낼 때 이 정보를 사용한다.*/
/*필드에 저장하기 -> e.getErrorCode().getHttpStatus()로 HTTP 상태코드를 꺼낼 때 이 정보를 사용한다.*/

/*여쭤보고 싶은 거!
* 뭔가 exception에서 각각의 파일로 여러 개 구현해놨는데, 오히려 난잡해보일 수도 있겠다는 생각이 들어서요..
* 혹시 businessexception 안에 static 내부 클래스로 넣는 방법과, 파일을 분리하는 방법 중에 무엇이 좋을까요..?
* 무슨 기준으로 어떻게 결정하는 게 좋을까요?*/