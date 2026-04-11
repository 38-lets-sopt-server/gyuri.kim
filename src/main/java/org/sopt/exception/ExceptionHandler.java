package org.sopt.exception;

import org.sopt.dto.response.CreatePostResponse;

public class ExceptionHandler {
    public CreatePostResponse handleId (PostNotFoundException e){
        System.out.println(e.getMessage());
        return new CreatePostResponse(null, e.getMessage());
    }
    public CreatePostResponse handleInvalid (IllegalArgumentException e){
        System.out.println(e.getMessage());
        return new CreatePostResponse(null, "🚫 " + e.getMessage());
    }
}

