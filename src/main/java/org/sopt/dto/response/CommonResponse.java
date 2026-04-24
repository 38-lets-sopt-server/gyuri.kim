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
    } //fail를 1주차에서는 하드코딩되어 있지만,
    // 에러 메시지는 상황마다 달라야 하니까
    // fail 메서드가 에러 메시지를 외부에서 받아야 한다-> message로 수정했어요!
}

