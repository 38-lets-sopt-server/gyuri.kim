package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.exception.PostNotFoundException;
import org.sopt.validator.PostValidator;
import org.sopt.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    // CREATE -> PostValidator로 옮기자
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
        //일단 post 타입으로 제한한다. 레포지토리에서 posts를 그대로 가져온다.
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
        //위와는 달리, id로 불러오기
        if (post==null){
        //    throw new IllegalArgumentException("id에 해당하는 게시글 없음");
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
            throw new IllegalArgumentException("id에 해당하는 게시글 없음");
        }
        if (newTitle == null || newTitle.isBlank()){
            throw new IllegalArgumentException("제목 추가하세요");
        }
        if (newTitle.length() > 50){
            throw new IllegalArgumentException("50자 제한 초과");
        }
        post.update(newTitle, newContent);
    }
//여기서 response를 만들지 않는 이유... 로는 updatePost가 변경을 목적으로 하고 있어서..라고 한다.
    //서버의 효율적인 역할 분담을 위해서..라고 하는데, "변경"이 목적이기 때문에 "조회"까지는 역할을 맞지 않아도 된다는 것으로 이해했다.
    //서버의 역할 분담에 있어서 개발자가 어떻게 반환할 것이고, 화면 설계서에 따라 어떤 기능까지의 역할을 하나에 담을 것인지가 중요할 것 같았다.
//여기서 post에서 내용물을 바꾸는 것은 통째로 수정이 아니라, 일부를 바꾸는 것이기 때문
    //변수값만 교체하는 것일 뿐. 객체는 그대로 남아있다.

    // DELETE 📝 과제
    public void deletePost(Long id) {
        // TODO
        Post post = postRepository.findById(id);
        if(post==null){
            throw new IllegalArgumentException("삭제할 게시글 없음");
        }
        postRepository.deleteById(id);
    }
}
//근데, PostRepository에서 boolean을 사용해서 구현했던 것이 기억나서 해보려고도 했습니다
//boolean isDeleted = postRepository.deleteById(id);
//if(!isDeleted){
// throw new Illegal~~

//위와 달리 여기서는 postRepository에서 deleteById를 하게 되었는데, 이유는 아예 ArrayList에서 객체를 삭제하는 것이기 때문.
