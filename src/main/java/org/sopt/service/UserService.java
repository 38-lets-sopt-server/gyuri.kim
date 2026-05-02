package org.sopt.service;
import org.sopt.domain.User;
import org.sopt.dto.request.CreateUserRequest;
import org.sopt.dto.response.UserResponse;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long create(CreateUserRequest request) {
        User user = new User(request.nickname(), request.email());
        return userRepository.save(user).getId();
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}