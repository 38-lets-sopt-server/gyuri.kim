//400 에러
package org.sopt.exception;

public class BadRequestException extends BusinessException{
    public BadRequestException (ErrorCode errorCode){
        super(errorCode);
    }
}
