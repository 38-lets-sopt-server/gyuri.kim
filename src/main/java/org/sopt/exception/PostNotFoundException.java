//존재하지 않는 ID로 조회/수정/삭제 시도 시 PostNotFoundException 커스텀 예외 생성

package org.sopt.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super("존재하지 않는 ID입니다");
        //super는 postnotfoundexception의 부모 클래스인 runtimeexception
        //의 메시지에 super 속의 내용을 넣어달라고 요청을 보내는 역할을 한다.
    }
}