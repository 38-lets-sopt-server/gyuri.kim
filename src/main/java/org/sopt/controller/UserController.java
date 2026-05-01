package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.sopt.dto.request.CreateUserRequest;
import org.sopt.dto.response.BaseResponse;
import org.sopt.dto.response.UserResponse;
import org.sopt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/v1/users -> 201 Created
    @Operation(summary = "유저 생성", description = "새로운 유저를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "유저 생성 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패 (이메일 형식 오류 등)")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<Long>> createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        // userService.create()가 생성된 유저의 ID를 반환한다고 가정했습니다.
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(userService.create(request)));
    }

    // GET /api/v1/users/{id} -> 200 OK
    @Operation(summary = "유저 단건 조회", description = "유저 ID로 특정 유저의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음 — 존재하지 않는 ID로 요청한 경우")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(
            @Parameter(description = "유저 ID", example = "1", required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(BaseResponse.success(userService.getUser(id)));
    }

    // DELETE /api/v1/users/{id}
    @Operation(summary = "유저 삭제", description = "유저를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}