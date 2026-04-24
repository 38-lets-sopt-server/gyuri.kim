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
import org.sopt.dto.response.CommonResponse;
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
    //CreatePostResponse와는 달리, 게시글 하나 생성해서 응답을 하나 받는 게 아니라
    //게시글을 전체 조회해서 응답을 여러 개 받으니까, Response를 반환받을 때, <List<>> 로 묶어주었다
    //GET 요청은 Body 가 없다.. 그러면 Request.. 가 없다?
    //그러면 어노테이션이 필요없..겠지?
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

        //여기서 200 OK 하면 성공했고, 데이터가 있다는 뜻이고
        //204 OK는 데이터는 없다는 뜻인데, 반환할 게 없으니까 204 OK가 좀 더 맞는 것 같다.

        //근데 혹시 noContent().build() [204 No Content] 와 ResponseEntity.ok().build() [200 OK] 의 차이점..이 무엇일까요..?ㅜㅜ
        //200 OK + 데이터 없음을 표시하면 그게 204 OK.. 아닌가..해서요..!
    }

    //@PathVariable은 id 꺼내는 것! @RequestBody는 Body에 있는 JSON을 UpdatePostRequest 로 변환하는 것!

    // DELETE /posts/{id}
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    //성공 시, 204 No Content
}