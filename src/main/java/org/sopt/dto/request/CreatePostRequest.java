package org.sopt.dto.request;

public record CreatePostRequest(
        Long userId,
        String title,
        String content,
        String author
){}
