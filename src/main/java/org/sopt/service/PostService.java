package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.UserRepository;
import org.sopt.exception.ErrorCode;
import org.sopt.exception.NotFoundException;
import org.sopt.domain.User;
import org.sopt.repository.PostRepository;
import org.sopt.validator.PostValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {
        Long savedPostId = savePostToDb(request);
        try {
            System.out.println("게시글이 저장되었습니다." + savedPostId);
        } catch(Exception e){
            System.err.println("에러발생! : " + e.getMessage());
        }
        return new CreatePostResponse(savedPostId, "게시글 등록 완료!");
    }

    @Transactional
    public Long savePostToDb (CreatePostRequest request){
        PostValidator.validatePost(request.title(), request.content());

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Post post = new Post(request.title(), request.content(), user);
        postRepository.save(post);

        return post.getId();
    }


    //READ - 전체 -> 게시판 목록 확인
    /* 조회 전용 -> 더티 채킹 안 함 -> 성능 최적화*/
    @Transactional (readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    // READ - 단건 -> 특정 글 가져와서 상세 내용 보여주기 -> 원본 꺼내고 검증하고, 반환하기
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.from(post);
    }

    // UPDATE
    @Transactional
    /*3주차 세미나 변경 사항_ 더티체킹으로 save 없이 자동 update한다. */
    public PostResponse updatePost(Long id, UpdatePostRequest request) { //파라미터 변경함
        PostValidator.validatePost(request.title(), request.content());
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        post.update(request.title(), request.content());
        return PostResponse.from(post);
    }

    // DELETE
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        postRepository.delete(post);
    }
}
