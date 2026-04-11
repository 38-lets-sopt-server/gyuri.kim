package org.sopt.dto.response;

public class CreatePostResponse {
    public Long id; //public 추가함
    public String message; //public 추가함

    public CreatePostResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}