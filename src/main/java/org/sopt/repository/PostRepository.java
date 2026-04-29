// Post 테이블 전용

package org.sopt.repository;
import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*3주차 내용 : 직접 구현한 인메모리 저장소 -> 인터페이스만 선언하면 된다.*/
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}