package org.sopt.controller;

import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CommonResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.service.PostService;
import org.sopt.dto.response.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    // POST /posts
    @PostMapping
    public ResponseEntity<CommonResponse<CreatePostResponse>> createPost(
            @RequestBody CreatePostRequest request
    ) {
        CreatePostResponse response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.success(response));
    }
    //얘는 201 Created!

    // GET /posts
    @GetMapping
    public ResponseEntity<CommonResponse<List<PostResponse>>> getAllPosts( ) {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.ok(CommonResponse.success(response));
        //성공 시 200 OK
    }

    // GET /posts/{id}
    //게시글 상세 조회
    @GetMapping("/{id}")
    // {id}로 받으려면.. @PathVariable 로 받아야한다..
    public ResponseEntity<CommonResponse<PostResponse>> getPost(@PathVariable Long id) {
        PostResponse response = postService.getPost(id);
        return ResponseEntity.ok(CommonResponse.success(response));
        //성공 시, 200 OK
    }

    // PUT /posts/{id}
    //게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long id,
            @RequestBody UpdatePostRequest request
    ) {
        postService.updatePost(id, request);
        return ResponseEntity.noContent().build();
    }

    //@PathVariable은 id 꺼내는 것! @RequestBody는 Body에 있는 JSON을 UpdatePostRequest 로 변환하는 것!

    // DELETE /posts/{id}
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}