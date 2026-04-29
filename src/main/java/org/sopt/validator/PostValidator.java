package org.sopt.validator;

public class PostValidator {
    public static void validatePost(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다!");
        }
        if (title.length() > 50){
            throw new IllegalArgumentException("50자 제한을 초과했습니다.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수입니다!");
        }
    }
}
