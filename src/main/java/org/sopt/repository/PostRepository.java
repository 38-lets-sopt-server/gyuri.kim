package org.sopt.repository;
import org.sopt.domain.Post;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final List<Post> postList = new ArrayList<>();
    private Long nextId = 1L;

    public Post save(Post post) {
        postList.add(post);
        return post;
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findById(Long id) {
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    } //id가 같은 것만 골라내서 그 중 첫 번째를 찾고, 없으면 null을 반환한다.

    public boolean deleteById(Long id) {
        return postList.removeIf(p -> p.getId().equals(id));
    }
//id가 일치하는 게 있으면 지우고 true를 반환한다. 만약 id가 일치하지 않으면 false를 반환한다.
    public Long generateId() {
        return nextId++;
    }
}
