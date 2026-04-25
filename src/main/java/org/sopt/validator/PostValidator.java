package org.sopt.validator;

public class PostValidator {
    //createpostresponse에 해당하는 에러 잡기
    public static void validatePost(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다!");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다!");
        }
    }
}
