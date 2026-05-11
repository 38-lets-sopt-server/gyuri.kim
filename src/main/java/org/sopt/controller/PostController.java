package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.BaseResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.service.PostService;
import org.sopt.dto.response.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Post", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // POST /posts -> 201 created
    @Operation(summary = "게시글 생성", description = "새로운 게시글을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패 (제목/내용 누락 또는 글자 수 초과)"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자 ID로 요청한 경우")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CreatePostResponse>> createPost(
            @Valid @RequestBody CreatePostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(postService.createPost(request)));
    }

    // GET /posts -> 200 OK
    @Operation(summary = "게시글 조회", description = "전체 게시글을 조회합니다.")
    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts( ) {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    // GET /posts/{id} -> 200 OK
    @Operation(summary = "게시글 단건 조회", description = "게시글 ID로 특정 게시글을 조회합니다. 삭제된 게시글은 조회되지 않아요.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 — ID가 숫자가 아닌 경우수"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음 — 존재하지 않는 ID로 요청한 경우")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(
            @Parameter(description = "게시글 ID", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.success(postService.getPost(id)));
    }

    // PUT /posts/{id}
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestBody UpdatePostRequest request
    ) {
        PostResponse response = postService.updatePost(id, request);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    //@PathVariable은 id 꺼내는 것! @RequestBody는 Body에 있는 JSON을 UpdatePostRequest 로 변환하는 것!

    // DELETE /posts/{id}
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}