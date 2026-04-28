## 1주차 세미나(이론) 내용 정리
서버의 응답 방식 - SSR vs CSR

- SSR 
: 서버가 완성된 화면을 생성 (server-side Rendering) → Next.js, Nginx

- CSR
: 클라이언트가 화면을 생성 (client-slide Redndering) → React, Vue, S3+CloudFront(CDN), Netlify

>WS (웹 서버_Nginx_정적 파일 서빙 + 리버스 프록시) 와 WAS (웹 어플리케이션 서버_비즈니스 로직 처리) 분리

→ 장애 격리 / 로드밸런싱 때문

.
### 에브리타임

- **앱 아이콘, 버튼 같은 UI 에셋**
    - 앱 빌드 시 번들에 포함됨. App Store / Play Store에서 앱을 설치할 때 같이 받아지는 것. 서버가 관여하지 않는다.


- **게시글에 올린 이미지**
    - S3 같은 오브젝트 스토리지에 저장되고, DB에는 그 URL만 저장. 앱이 이미지를 보여줄 때는 S3(또는 CDN)에 직접 요청한다.


- **게시글 목록, 댓글, 좋아요 수 같은 데이터**
    - 앱이 서버에 요청하면, 서버가 DB에서 꺼내서 JSON으로 응답해요.


- **Nginx**
    - → 앱의 API 요청을 받아서 Spring WAS로 전달하는 리버스 프록시 역할을 해요.

    .
### 자바

- 장점 1. 자동 메모리 관리 (GC) 
: C언어처럼 메모리를 직접 해제하지 않아도 됨 → 비즈니스 로직에 집중

- 장점 2. JVM 기반 성능 
: JIT 컴파일 등의 최적화 기법 → 빠른 처리 속도

JVM → Java는 write once, run anywhere
### 스레드


: 프로세스 안에서 실제로 작업을 처리하는 단위

- 같은 프로세스의 메모리를 공유 → 프로세스를 새로 만드는 것보다 훨씬 가볍고, 빠르게 생성 가능

: 스레드가 많을수록 무조건 좋은 건 아니다. context switching(비용) 발생


### 메모리 구조


: 코드 영역 - 실행할 명령어들

- 데이터 영역 - 전역 변수, 정적 변수

- 힙 - 동적으로 생성된 것들

- 스택 - 메서드 호출 정보, 지역 변수

new post → post객체는 힙 영역에 생성

>(객체를 가리키는 변수) post post → 스택 영역에 저장 (=참조타입)



## 1주차 세미나(개발) 내용 정리

### 구조
클래스 안에 필드들을 작성..

그 안에 생성자 -> 인스턴스를 만들 때 초기값을 세팅
public Post (Long id, ~~) {this.id = id; this.title = title; ~~;}}

인스턴스 생성 Post post = new Post (~~)

### Getter/Setter
메서드를 통해서 값을 꺼내거나 바꾼다.

1. Getter - 값을 꺼낼 때 사용
   public String getTitle(){
   return this.title;
   }

2. Setter - 값을 바꿀 때 사용
   public void setTitle(String title){
   this.title = title;
   }

### 역할에 따른 계층 나누기
- Domain : 핵심 데이터 구조 : Post 클래스
- Repository : 데이터 저장, 조회 : PostService의 postList 부분
- Service : 비즈니스 로직 처리 : PostService의 CRUD 메서드
- Controller : 요청을 받아 서비스를 호출하고 응답을 돌려줌 : Main의 Scanner 입력 부분

>Post는 게시글 데이터를 보관할 책임이 있다 -> 게시글 생성 로직 수정할 때

>PostService는 CRUD 로직을 처리할 책임이 있다. -> 게시글 데이터 구조 바꿀 때



## 1주차 과제 개발을 하며..
1. 예외의 '정의'와 '처리' 역할 분리

- PostNotFoundException과 같은 커스텀 클래스는 예외를 정의하는 역할이고, ExceptionHandler는 발생한 예외를 처리하는 역할임을 명확히 인지했다.


- 기존 컨트롤러 내부에 작성했던 불필요한 try-catch 블록과 println 코드를 모두 제거하고, 컨트롤러는 비즈니스 로직에만 집중하도록 구조를 개선했다.


2. ExceptionHandler 도입과 반환 타입 충돌 문제

- 게시글이 없을 때의 PostNotFoundException과 기타 입력 오류인 Illegal Argument Exception을 각각 담당하는 두 개의 예외 처리 메서드를 ExceptionHandler에 구현했다.


- 하지만 예외 발생 시 기존 컨트롤러가 반환하던 타입(예: CreatePost, PostResponse 등)이 제각각이어서 핸들러에서 어떤 타입으로 반환해야 할지 충돌하는 문제가 발생했다.

3. 공통 응답 DTO 설계 결론

- ExceptionHandler의 반환 타입을 하나로 통일하고 클라이언트에게 일관된 에러 응답을 내려주기 위해, 미뤄두었던 공통 응답 DTO 구조를 설계하기로 결정했다.

4. 자바 기초 문법 보완 필요성 (Self-Feedback)

- 메서드 선언부의 구조(public, static, final, 반환 타입, 메서드명, 파라미터)가 아직 헷갈리는 부분이 있어 기본기 복습이 필요하다.


- 특히 제네릭 메서드를 작성할 때 static 뒤에 타입 파라미터(예: <T>)가 오는 문법 구조에 대해 추가적인 개념 정립이 필요하다.


## 💡 와인잔조 코드리뷰하며 배운 점

[규일님]

- DTO 간소화
  : DTO를 record로 구현하면 생성자와 getter가 자동 생성되어 보일러플레이트 코드를 크게 줄일 수 있다.

- 날짜 타입 유지
  : createdAt 등의 시간 데이터는 String으로 포맷팅하기보다 LocalDateTime 타입을 그대로 유지하는 것이 추후 날짜 계산이나 비교 로직에 유리하다.

- 예외 처리
  : 현재의 단순한 아키텍처에서는 예외를 개별적으로 잡는 것보다 RuntimeException으로 일괄 처리하는 것이 코드 가독성에 좋다.

[채현님]

- 캡슐화
  : DTO 필드는 private으로 선언하고 getter를 두어 객체의 캡슐화를 지키는 것이 정석이다. (더 간결한 설계를 원한다면 record로 대체 가능)

[성휘님]

- 메서드별 예외 분리
  : create, get, update, delete 등 각 메서드의 역할과 상황에 맞춰 발생 가능한 예외만 선택적으로 명확히 처리해야 한다.

- 커스텀 예외 개선
  : PostNotFoundException 생성 시 String 메시지 대신 식별자(Long id)를 파라미터로 받도록 설계하면, 내부에서 일관된 예외 메시지를 자동 생성할 수 있어 유지보수성이 높아진다.

[자윤님]

- Stream API 활용
  : 컬렉션 순회 및 변환 시 전통적인 for문 대신 stream().map().toList() 패턴을 적용하면 코드가 훨씬 선언적이고 간결해진다.

### 🗣️ 내가 드린 피드백
- 예외 처리의 의미론적 적절성 고려 (IllegalArgumentException vs PostNotFoundException)

> 조회한 post가 null일 때 IllegalArgumentException을 던지는 것은 예외의 본래 목적과 맞지 않다.
> Illegal Argument Exception은 메서드에 전달된 '입력값 자체'가 잘못되었을 때 사용하는 예외이다.

해당 상황은 입력값의 문제가 아니라 '요청한 ID에 해당하는 게시글 자원이 존재하지 않는 상태'를 의미한다.
따라서 도메인 맥락을 정확히 반영하여 PostNotFoundException을 발생시키는 것이 시맨틱(Semantic)하게 더 적절하다는 피드백을 남겼다.