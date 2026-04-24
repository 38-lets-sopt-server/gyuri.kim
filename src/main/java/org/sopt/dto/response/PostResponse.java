package org.sopt.dto.response;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author,
        String createdAt
) {}

//이것도 record로 바꿨습니다