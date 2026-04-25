package org.sopt.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("POST_001: 게시글을 찾을 수 없습니다. id: " + id);
    }
} // 필수과제 구현사항.. POST_001 만들기