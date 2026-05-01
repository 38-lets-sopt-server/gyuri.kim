// Post 테이블 전용

package org.sopt.repository;
import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*3주차 내용 : 직접 구현한 인메모리 저장소 -> 인터페이스만 선언하면 된다.*/
//fetch join 적용해보기!
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT DINSTINCT p FROM Post p" +
    "JOIN FETCH p.user" +
    "LEFT JOIN FETCH p.likes")
    List<Post> findWithUserLikes();
}
//distinct를 사용해서 JPA가 중복된 Post 엔티티들을 하나로 합치게 했다
//작성자 정보와 좋아요 정보를 같이 가져오게 했다