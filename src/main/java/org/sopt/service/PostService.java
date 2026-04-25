package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.validator.PostValidator;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public CreatePostResponse createPost(CreatePostRequest request) {
        PostValidator.validatePost(request.title(), request.content());
        String createdAt = java.time.LocalDateTime.now().toString();
        Post post = new Post(postRepository.generateId(), request.title(), request.content(), request.author(), createdAt);
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }
    // READ - 전체 📝 과제 -> 게시판 목록 확인
    public List<PostResponse> getAllPosts() {
        List<Post>posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for(Post post : posts){
            PostResponse item = new PostResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getAuthor(),
                    post.getCreatedAt()
            );
            responses.add(item);
        }
        return responses;
    }

    // READ - 단건 📝 과제 -> 특정 글 가져와서 상세 내용 보여주기 -> 원본 꺼내고 검증하고, 반환하기
    public PostResponse getPost(Long id) {
        // Optional 로 수정.
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt()
        );
    }

    // UPDATE 📝 과제 -> 고칠 글이 있는 지 id로 찾고, 50자 제한 확인한다.
    //Optional<Post>를 Post로 못 받는다..
    public void updatePost(Long id, UpdatePostRequest request) { //파라미터 변경함
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        if (request.title() == null || request.title().isBlank()){
            throw new IllegalArgumentException("제목 추가하세요");
        }
        if (request.title().length() > 50){
            throw new IllegalArgumentException("50자 제한 초과");
        }
        post.update(request.title(), request.content());
    }

    // DELETE 📝 과제
    public void deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        postRepository.deleteById(id);
    }
}