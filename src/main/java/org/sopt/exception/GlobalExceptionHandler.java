//예외를 잡아서 HTTP 응답으로 변환.!

package org.sopt.exception;
import org.sopt.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //비즈니스 예외 처리 -> 커스텀 예외
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<?>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(BaseResponse.error(errorCode.getMessage()));
    }

    //자바 예외 처리 -> 400 에러
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<?>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(BaseResponse.error(e.getMessage()));
    }

    //Controller에서 @Valid 검증 실패 시 MethodArgumentNotValidException 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleValidationException(
            MethodArgumentNotValidException e
    ) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("유효성 검증에 실패했습니다.");
        return ResponseEntity.status(BAD_REQUEST)
                .body(BaseResponse.error(errorMessage));
    }

    //500 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleException(Exception e) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error("서버 내부 오류 발생!"));
    }
}