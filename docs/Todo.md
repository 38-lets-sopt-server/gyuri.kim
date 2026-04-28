## 1주차 과제

- [ ] 세미나에서 뼈대로 남겨둔 Read(전체) / Read(단건) / Update / Delete 를 main에 맞게 직접 완성해주세요.


- [ ] 존재하지 않는 ID로 조회/수정/삭제 시도 시 PostNotFoundException 커스텀 예외를 만들어 던져주세요


- [ ] 클래스 간 책임 분리를 명확하게 해주세요. PostService 안에 섞여있는 유효성 검증 로직을 별도 클래스로 분리해보세요


- [ ] Main을 클라이언트라고 가정했는데요. 클라이언트(Main)가 서버(Controller)로부터 받는 응답을 일관된 형식으로 처리할 수 있도록, 공통 응답 객체를 설계하고 적용해보세요.



## 1주차 코드 리뷰
1. 예외 처리 및 응답 구조 개선
- [ ] 에러 관리 체계 구축: ErrorCode Enum을 신설하여 에러 코드(예: INVALID_POST_ID)와 기본 메시지를 통합 관리하기


- [ ] 메서드 오버로딩 활용: 상황에 따라 사용자 정의 메시지를 직접 전달할 수 있도록 응답 메서드 확장하기


- [ ] Global Exception Handler 적용: ExceptionHandler의 반환 타입을 ApiResponse<?>로 통일하여 다양한 응답 DTO를 하나의 공통 객체로 처리하기


- [ ] 예외 메시지 전달: PostNotFoundException 발생 시 e.getMessage()를 활용하여 실패 응답 반환하기


2. 검증(Validation) 로직 정교화
- [ ] 검증 책임 분리: updatePost 내부에 흩어져 있는 검증 로직을 PostValidator로 이관하여 검증 책임 일원화하기


- [ ] 검증 규칙 일관성 확보: update 시에도 create와 동일하게 content 검증 로직 추가하기 (공백 및 길이 체크)


- [ ] Validator 구조 설계: create와 update의 공통 검증 로직과 개별 검증 메서드를 구분하여 관리하기

3. DTO 및 네이밍 최적화
- [ ] 응답 DTO 분리: 현재 공통으로 사용 중인 PostResponse를 각 API 기능에 맞춰 분리하기 (향후 유지보수 시 사이드 이펙트 방지)


- [ ] 메서드 네이밍 수정: handleInvalid를 조금 더 직관적인 handleInvalidRequest로 변경하기


4. 코드 컨벤션 확인
- [ ] 메서드 선언 순서 숙지: [접근제어자] [지정자(static)] [반환타입(ApiResponse<T>)] [메서드명] 순서에 맞춰 코드 스타일 체크하기


## 2주차 과제
- PostRepository — @Repository 추가, Optional 반환


- PostService — @Service + Spring DI, 반환 타입이 void → DTO


- PostController — @RestController + HTTP 메서드 매핑, ResponseEntity로 상태 코드까지


- Main — 클라이언트 역할 사라짐, Spring이 대신 요청을 받아줌


- [ ] PostNotFoundException 커스텀 예외 클래스를 만들고, @RestControllerAdvice로 적절한 HTTP 상태 코드와 함께 응답하도록 구현해주세요. 에러 코드 체계도 함께 고민해봐요 (예: POST_001: 게시글을 찾을 수 없습니다)


- [ ] ApiResponse<T> 공통 응답 객체를 설계하고, 모든 API의 성공/실패 응답이 일관된 형식을 갖도록 적용해주세요


- [ ] 완성한 API 전체를 Postman으로 테스트하고, 각 API의 성공/실패 응답 화면을 캡처해서 PR에 첨부해주세요


- [ ] 게시글 목록 조회 API에 Pagination을 적용해주세요. page와 size 쿼리 파라미터를 받아서 해당 페이지의 게시글 목록을 반환하도록 구현해주세요. (예: GET /posts?page=0&size=10)


- [ ] PostRepository를 인터페이스로 추출하고, 기존 구현체를 InMemoryPostRepository로 이름을 바꿔 구현해주세요. PostService가 인터페이스에만 의존하도록 수정하고 Spring DI로 주입받도록 해주세요


- [ ] 에러 코드를 enum으로 관리해보세요. ErrorCode enum에 상태 코드와 메시지를 함께 정의하고, GlobalExceptionHandler가 이를 활용하도록 구성해주세요


- [ ] 게시판 종류(FREE, HOT, SECRET)를 boardType 필드로 추가하고, 게시판 종류별로 게시글을 조회하는 API를 구현해주세요. boardType은 enum으로 정의해주세요


## 2주차 코드 리뷰
- [ ] 전역 예외 처리기(Global Exception Handler) 구현: 고려하지 못한 예외 일괄 처리 로직 추가


- [ ] 커스텀 예외 구조 개선: BaseException을 정의하고 모든 커스텀 예외가 이를 상속받도록 설계하여 GEH 관리 효율화


- [ ] 도메인 모델 검증 강화: Service 레이어 대신 Entity 내에서 검증 수행 및 .orElseThrow 활용


- [ ] 응답 DTO(CommonResponse) 안정화: record 타입으로 전환하거나 접근 제어자 및 Getter 추가로 JSON 직렬화 보장


- [ ] 저장소 캡슐화: Repository에서 리스트 반환 시 new ArrayList<>()를 사용하여 내부 상태 보호(방어적 복사)


- [ ] 검증 로직 통일: createPost와 updatePost에서 사용하는 검증 방식을 Validator 활용으로 단일화


- [ ] 글자 수 제한 로직 추가: Validator 내에 50자 제한 검증 로직 반영


- [ ] 정적 팩토리 메소드 도입: 객체 생성 시 from 메소드를 활용하여 가독성 및 유지보수성 향상


- [ ] 에러 메시지 관리 체계화: PostNotFoundException의 메시지 포맷(POST_001 등)을 별도 상수나 Enum으로 분리 관리

## 3주차 과제

- Post — 일반 클래스 → @Entity로 DB 테이블과 매핑


- **PostRepository** — 직접 구현한 ArrayList 저장소 → JpaRepository


- PostService — ArrayList 로직 → JpaRepository + @Transactional


- [ ] User 엔티티를 만들고, Post에 작성자 연관관계를 추가. 게시글 작성 시 userId를 받아서 연결하기


- [ ] 전체 API에 Swagger 어노테이션을 적용하고, Swagger UI에서 API를 직접 테스트하기. 테스트 화면을 PR에 첨부


- [ ] BaseTimeEntity를 게시글에 적용하기


- [ ] Like 엔티티를 만들고, 좋아요 추가/취소 API를 구현하기.
  같은 유저가 같은 게시글에 중복 좋아요를 누를 수 없게 예외처리하기


- [ ] 게시글 목록 조회 시 좋아요 수도 함께 반환해주기. N+1 문제가 발생하지 않도록 fetch join을 적용하고, 
적용 전후 실행되는 쿼리를 show-sql: true로 확인해서 PR에 비교해서 첨부하기


- [ ] 좋아요 동시성 문제를 @Version으로 해결하고, 낙관적 락 충돌 시 재시도 로직을 구현하기


- [ ] 게시글을 제목으로 검색하는 API를 JPQL @Query를 사용해서 구현해주세요. 검색 결과에 작성자 닉네임도 함께 반환하고, fetch join으로 N+1이 발생하지 않도록 하기


- [ ] PostRepositoryCustom 인터페이스와 PostRepositoryCustomImpl 구현체를 만들고, 위의 검색 기능을 QueryDSL로 재구현하기
제목 키워드와 작성자 닉네임을 동시에 받아 동적으로 조건을 조합해주세요. PostRepository가 JpaRepository와 PostRepositoryCustom을 함께 상속하도록 구성하기