package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.ExceptionHandler;
import org.sopt.exception.PostNotFoundException;
import org.sopt.validator.PostValidator;
import org.sopt.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    public CreatePostResponse createPost(CreatePostRequest request) {
        //심화과제2
        PostValidator.validatePost(request.title, request.content);
//        if (request.title == null || request.title.isBlank()) {
//            throw new IllegalArgumentException("제목은 필수입니다!");
//        }
//        if (request.content == null || request.content.isBlank()) {
//            throw new IllegalArgumentException("내용은 필수입니다!");
//        }
        String createdAt = java.time.LocalDateTime.now().toString();
        Post post = new Post(postRepository.generateId(), request.title, request.content, request.author, createdAt);
        postRepository.save(post);
        return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
    }
    // READ - 전체 📝 과제 -> 게시판 목록 확인
    public List<PostResponse> getAllPosts() {
        // TODO
        List<Post>posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        //사용자가 보게 될 반환값
        //서버 개발에서는 보안이나 효율성을 위해 내부 데이터를 그대로 내보내지 않고, 보여줄 데이터만 추린
        //Response 객체에 옮겨 담아서 내보내는 게 원칙이라는 글을 보아서, 이렇게 작성해 보았습니다.
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
        // TODO
        Post post = postRepository.findById(id);
        if (post==null){
        //심화 과제
            throw new PostNotFoundException(id);
        }
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt()
        );
    }

    // UPDATE 📝 과제 -> 고칠 글이 있는 지 id로 찾고, 50자 제한 확인한다.
    public void updatePost(Long id, String newTitle, String newContent) {
        // TODO
        Post post = postRepository.findById(id);
        if (post==null){
            throw new PostNotFoundException(id);
        }
        if (newTitle == null || newTitle.isBlank()){
            throw new IllegalArgumentException("제목 추가하세요");
        }
        if (newTitle.length() > 50){
            throw new IllegalArgumentException("50자 제한 초과");
        }
        post.update(newTitle, newContent);
    }

    // DELETE 📝 과제
    public void deletePost(Long id) {
        // TODO
        Post post = postRepository.findById(id);
        if(post==null){
            throw new PostNotFoundException(id);
        }
        postRepository.deleteById(id);
    }
}