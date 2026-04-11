package org.sopt.dto.response;

//성공여부
//제너릭 -> 무슨 데이터 썼는지
//에러/성공 메시지

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
    public static <T> CommonResponse<T> fail(T data){
        return new CommonResponse<T>(false, null, "fail");
    } //여기서 fail일 때, errormessage..를 출력해야 하는 건 알겠는데.. 이걸 어디서 가져와야 하는지 감이 안잡혀요ㅠㅠ
}

