## 1주차 세미나 내용 정리

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
