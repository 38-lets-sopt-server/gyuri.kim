package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.service.PostService;
import org.sopt.dto.response.PostResponse;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    // POST /posts
    public CreatePostResponse createPost(CreatePostRequest request) {
        try {
            return postService.createPost(request);
        } catch (PostNotFoundException e) { //심화 과제1
            System.out.println("존재하지 않는 ID입니다" + e.getMessage());
            return new CreatePostResponse(null, e.getMessage());
        } catch (IllegalArgumentException e) {
            return new CreatePostResponse(null, "🚫 " + e.getMessage());
        }
    }

    // GET /posts 📝 과제
    public List<PostResponse> getAllPosts() {
        // TODO: postService.getAllPosts() 호출해서 반환
        return postService.getAllPosts();
    }

    // GET /posts/{id} 📝 과제
    public PostResponse getPost(Long id) {
        // TODO: postService.getPost(id) 호출, 예외 발생 시 null 반환
        try{
            return postService.getPost(id);
        } catch (PostNotFoundException e) { //심화 과제1
            System.out.println("존재하지 않는 ID입니다" + e.getMessage());
            return null;
        }catch (IllegalArgumentException e){
            System.out.println("에러 메시지" + e.getMessage());
            return null;
        }
    }
    //e.getMessage를 통해 문자열이 예외 객체 안에 편지처럼 저장된다.
        // IllegalArgumentException : 적절하지 않은 인자 예외, 어떤 이유 때문에 예외가 발생했는지 메시지를 남기기 위해 사용한다.
    // PUT /posts/{id} 📝 과제
    public void updatePost(Long id, String newTitle, String newContent) {
        // TODO: postService.updatePost() 호출, 예외 발생 시 에러 메시지 출력
        try{
            postService.updatePost(id, newTitle, newContent);
        } catch(PostNotFoundException e){  //심화 과제1
            System.out.println("존재하지 않는 ID입니다" + e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("에러 메시지" + e.getMessage());
        }

    }

    // DELETE /posts/{id} 📝 과제
    public void deletePost(Long id) {
        // TODO: postService.deletePost() 호출, 예외 발생 시 에러 메시지 출력
        try {
            postService.deletePost(id);
        } catch(PostNotFoundException e){  //심화 과제1
            System.out.println("존재하지 않는 ID입니다" + e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("에러 메시지" + e.getMessage());
        }
    }
}