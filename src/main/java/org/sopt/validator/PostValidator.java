package org.sopt.validator;

import org.sopt.exception.BadRequestException;
import org.sopt.exception.ErrorCode;

public class PostValidator {
    public static void validatePost(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new BadRequestException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (title.length() > 50){
            throw new BadRequestException(ErrorCode.TITLE_TOO_LONG);
        }
        if (content == null || content.isBlank()) {
            throw new BadRequestException(ErrorCode.CONTENT_REQUIRED);
        }
    }
}
