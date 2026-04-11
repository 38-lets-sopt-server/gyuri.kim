## 1주차 세미나(이론) 내용 정리
서버의 응답 방식 - SSR vs CSR

- SSR : 서버가 완성된 화면을 생성 (server-side Rendering) → Next.js, Nginx
- CSR : 클라이언트가 화면을 생성 (client-slide Redndering) → React, Vue, S3+CloudFront(CDN), Netlify

WS(웹 서버_Nginx_정적 파일 서빙 + 리버스 프록시)와 WAS(웹 어플리케이션 서버_비즈니스 로직 처리) 분리

→ 장애 격리 / 로드밸런싱 때문

### 에브리타임

- **앱 아이콘, 버튼 같은 UI 에셋**
    - → 앱 빌드 시 번들에 포함돼요. App Store / Play Store에서 앱을 설치할 때 같이 받아지는 거예요. 서버가 관여하지 않아요.
- **게시글에 올린 이미지**
    - → S3 같은 오브젝트 스토리지에 저장되고, DB에는 그 URL만 저장해요. 앱이 이미지를 보여줄 때는 S3(또는 CDN)에 직접 요청해요.
- **게시글 목록, 댓글, 좋아요 수 같은 데이터**
    - → 앱이 서버에 요청하면, 서버가 DB에서 꺼내서 JSON으로 응답해요.
- **Nginx**
    - → 앱의 API 요청을 받아서 Spring WAS로 전달하는 리버스 프록시 역할을 해요.

에브리타임 앱
↓ "게시글 목록 줘" (API 요청)
Nginx (리버스 프록시 + 로드밸런서)
↓
Spring WAS (게시글 조회 로직 실행)
↓
DB (게시글 데이터 꺼냄)
↓ JSON 응답
에브리타임 앱 (받은 데이터로 화면 그림)

### 자바

장점 1. 자동 메모리 관리 (GC) : C언어처럼 메모리를 직접 해제하지 않아도 됨 → 비즈니스 로직에 집중

장점 2. JVM 기반 성능 : JIT 컴파일 등의 최적화 기법 → 빠른 처리 속도

JVM → Java는 write once, run anywhere

### 스레드


: 프로세스 안에서 실제로 작업을 처리하는 단위

: 같은 프로세스의 메모리를 공유 → 프로세스를 새로 만드는 것보다 훨씬 가볍고, 빠르게 생성 가능

: 스레드가 많을수록 무조건 좋은 건 아니다. context switching(비용) 발생

### 메모리 구조


: 코드 영역 - 실행할 명령어들

: 데이터 영역 - 전역 변수, 정적 변수

: 힙 - 동적으로 생성된 것들

: 스택 - 메서드 호출 정보, 지역 변수

new post → post객체는 힙 영역에 생성

(객체를 가리키는 변수) post post → 스택 영역에 저장 (=참조타입)



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
Domain : 핵심 데이터 구조 : Post 클래스
Repository : 데이터 저장, 조회 : PostService의 postList 부분
Service : 비즈니스 로직 처리 : PostService의 CRUD 메서드
Controller : 요청을 받아 서비스를 호출하고 응답을 돌려줌 : Main의 Scanner 입력 부분

-> Post는 게시글 데이터를 보관할 책임이 있다 -> 게시글 생성 로직 수정할 때
-> PostService는 CRUD 로직을 처리할 책임이 있다. -> 게시글 데이터 구조 바꿀 때






## 1주차 과제 개발을 하며..
[1번 문제] PostNotFoundException.java가 과연 필요할까..

규일님과 소연님께 코드리뷰를 받아본 결과, postnotfoundexception.java보다는
exceptionhanlder를 만들어서 예외를 처리하는 것이 좋을 것 같다는 생각을 하게 되었다.
postnotfoundexception.java는 예외를 정의하는 곳이고, exceptionhandler는 예외를 '처리'하는 곳이다.

이 두개의 차이를 몰라서, postcontroller에도 try catch catch 이러한 구조로 되고, postnotfoundexception도
있어서 뭔가 중복..되고 쓸데 없는 코드를 작성한 것 같다는 걸 느끼게 되었다.

(사실 규일님의 리뷰를 받고 소연님께 도움을 청하고 소연님이 첨부해주신 자료를 읽고 나서도 이해가 안되어서..
계속 유튜브, 블로그 찾아보며 예외처리에 대해 찾아보니.. 예외를 처리하였다고 생각한 postnotfoundexception.java는
예외를 그냥 정의..한 것이었다..)

그러면..controller에서는 println을 하지 않고, try catch도 없고..
그냥 id..만 반환하면 되는거겠지..

그렇다면 (1) PostNotFoundException을 잡는 메서드와 (2)IllegalArgumentException을 잡는 메서드,

총 2개의 메서드가 ExceptionHandler에 필요할 것이다.>!

### 오마이갓...ㅜㅜ
ExceptionHandler를 만들었다..
id가 없을때는 postnotfoundexception으로 하고, 그 외의 에러는 자바에서 제공하는 
illegal~~ 로 처리했다. 그리고 그 두개를 ExceptionHandler에 담았다.

근데 ExceptionHandler가 CreatePost도 반환하고, PostResponse를 반환해야 하는데.. 반환타입이 달라져야 한다..
근데 그렇다고 다 따로 쓰자니.. ExceptionHandler를 만든 이유가 없다..?

그래서 결국 미뤄놨던 DTO 구조를 만들기로...했다... (새벽 1시.. 내일 세미난데..ㅜㅜ)

근데 계속 자바기초가 부족해서인지.. 계속 헷갈린다..
public 반환타입 메서드이름 파라미터
이런 식으로 쓰는데
final, static 이런거는 반환타입 앞에 쓰는데
static 뒤에도 타입..?을 작성한다..?