package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.dto.response.BaseResponse;
import org.sopt.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "좋아요 관련 API")
@RestController
@RequestMapping("/api/v1")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Operation(summary = "좋아요 입력", description = "게시글에 좋아요를 누릅니다")
    //여기서는 apiresponse 안 받아도 될까요..? 받아야 할까요..?
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "좋아요 성공"),
            @ApiResponse(responseCode = "404", description = "유저나 게시글 없음"),
            @ApiResponse(responseCode = "409", description = "이미 좋아요를 누름")
    })
    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<BaseResponse<Void>> addLike(
            @RequestHeader("userId") Long userId,
            @PathVariable Long postId) {
        likeService.addLike(userId, postId);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "좋아요 취소", description = "게시글의 좋아요를 취소합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "취소 성공"),
            @ApiResponse(responseCode = "404", description = "좋아요를 누르지 않음")
    })
    @DeleteMapping("/posts/{postId}/likes")
    public ResponseEntity<BaseResponse<Void>> deleteLike(
            @RequestHeader("userId") Long userId,
            @PathVariable Long postId) {
        likeService.deleteLike(userId, postId);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}