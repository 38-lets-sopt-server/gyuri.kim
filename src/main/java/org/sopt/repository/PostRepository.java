package org.sopt.repository;
import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<Post> postList = new ArrayList<>();
    private Long nextId = 1L; //id long 타입이니까 L 붙여봤어요
    public Post save(Post post) {
        postList.add(post);
        return post;
    }
    public List<Post> findAll() {
        return postList;
    }
    public Optional<Post> findById(Long id) {  // null 대신 Optional
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public boolean deleteById(Long id){
        return postList.removeIf(p -> p.getId().equals(id));
    }
    public Long generateId(){ // 익명 1, 익명 2....
        return nextId++;
    }
}
