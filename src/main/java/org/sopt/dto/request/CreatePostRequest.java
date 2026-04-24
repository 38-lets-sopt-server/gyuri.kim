package org.sopt.dto.request;

public record CreatePostRequest(
    String title,
    String content,
    String author
){}

//1주차 코드리뷰 받은 걸 바탕으로 record 사용으로 수정했습니당~