package org.sopt.dto.response;

public class CommonResponse <T> {
    boolean success;
    T data;
    String message;

    public CommonResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
    public static <T> CommonResponse<T> success(T data){
        return new CommonResponse<T>(true, data, "success");
    }
    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<T>(false, null, message);
    }
}

