package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        Long userId,

        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 50, message = "제목은 50자를 초과할 수 없습니다.")
        @Schema(description = "게시글 제목 (최대 50자)", example = "오늘 학식 뭐임")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        @Size(max = 500, message = "내용은 500자를 초과할 수 없습니다.")
        @Schema(description = "게시글 내용 (최대 500자)", example = "돈까스래")
        String content,

        @NotBlank(message = "작성자는 필수입니다.")
        @Schema(description = "작성자 닉네임", example = "익명")
        String author
){}
