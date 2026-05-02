package org.sopt.dto.response;

import org.sopt.domain.Post;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author,
        long likeCount,
        String createdAt,
        String lastModifiedAt
) {
    public static PostResponse from (Post post){
        String nickname = (post.getUser() != null) ? post.getUser().getNickname() : "익명";
        String createdAt = (post.getCreatedDate() != null) ? post.getCreatedDate().toString() : "";
        String modifiedAt = (post.getLastModifiedDate() != null) ? post.getLastModifiedDate().toString() : "";

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                nickname,
                post.getLikes().size(),
                createdAt,
                modifiedAt
                //리스트의 크기로 좋아요를 계산한다.
                //fetch join을 사용하면 이미 메모리에 모든 좋아요에 관련된 데이터가 올라와있는 상태이기 때문에
                //Count를 하도록 시키는 것보다는 리스트의 크기로 좋아요를 계산하는 것이 효율적일 것 같아서 이렇게 작성했다!
                //앞에 (long)을 붙인거는 에러가 나서 붙인건데, 왜 붙여야 하는걸까욤...?

        );
    }
}
