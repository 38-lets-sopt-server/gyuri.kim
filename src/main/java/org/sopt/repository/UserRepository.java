//User 테이블 전용

package org.sopt.repository;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}


//여기서는 @Repository가 필요 없다고 한다. JpaRepository를 상속받으면, Spring이 자동으로 Bean으로 등록해주기 때문이다.