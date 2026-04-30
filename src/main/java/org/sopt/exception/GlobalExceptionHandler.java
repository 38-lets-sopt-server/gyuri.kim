//예외를 잡아서 HTTP 응답으로 변환.!

package org.sopt.exception;
import org.sopt.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //비즈니스 예외 처리 -> 커스텀 예외
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<?>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(CommonResponse.fail(errorCode.getMessage()));
    }

    //자바 예외 처리 -> 400 에러
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse<?>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(CommonResponse.fail(e.getMessage()));
    }

    //Controller에서 @Valid 검증 실패 시 MethodArgumentNotValidException 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<?>> handleValidationException(
            MethodArgumentNotValidException e
    ) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("유효성 검증에 실패했습니다.");
        return ResponseEntity.status(BAD_REQUEST)
                .body(CommonResponse.fail(errorMessage));
    }

    //500 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<?>> handleException(Exception e) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(CommonResponse.fail("서버 내부 오류 발생!"));
    }
}