package org.sopt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record BaseResponse<T>(
        boolean success,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, message, data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, "요청이 성공했습니다.", data);
    }

    // 실패 응답
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(false, message, null);
    }
}