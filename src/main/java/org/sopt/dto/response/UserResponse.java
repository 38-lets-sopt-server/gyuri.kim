package org.sopt.dto.response;

public record UserResponse(
        Long id,
        String nickname,
        String email
) {
    public static UserResponse from(org.sopt.domain.User user) {
        return new UserResponse(user.getId(), user.getNickname(), user.getEmail());
    }
}