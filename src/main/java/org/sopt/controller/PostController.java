package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.exception.ExceptionHandler;
import org.sopt.exception.PostNotFoundException;
import org.sopt.service.PostService;
import org.sopt.dto.response.PostResponse;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();
    // POST /posts
    public CreatePostResponse createPost(CreatePostRequest request) {
        return postService.createPost(request);
    }

    // GET /posts 📝 과제
    public List<PostResponse> getAllPosts() {
        // TODO: postService.getAllPosts() 호출해서 반환
        return postService.getAllPosts();
    }

    // GET /posts/{id} 📝 과제
    public PostResponse getPost(Long id) {
        // TODO: postService.getPost(id) 호출, 예외 발생 시 null 반환
        try {
            return postService.getPost(id);
        } catch (PostNotFoundException e) {
            return null;
        }
    }
    // PUT /posts/{id} 📝 과제
    public void updatePost(Long id, String newTitle, String newContent){
            // TODO: postService.updatePost() 호출, 예외 발생 시 에러 메시지 출력
        postService.updatePost(id, newTitle, newContent);
    }

    // DELETE /posts/{id} 📝 과제
    public void deletePost(Long id) {
        // TODO: postService.deletePost() 호출, 예외 발생 시 에러 메시지 출력
        postService.deletePost(id);
    }
}