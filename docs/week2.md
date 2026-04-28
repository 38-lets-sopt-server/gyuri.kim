## 2주차 세미나(이론) 내용 정리

### 1차 세미나에서 바뀐 점
- 입력 방식 : Scanner -> HTTP 요청 (@RequestBody, @PathVariable)
- 출력 방식 : System.out.println -> HTTP 응답 (ResponseEntity)
- 의존성 관리 : new로 직접 생성 -> Spring DI (@Service, @Repository)
- 통신 형식 : 훔수 직접 호출 -> REST API 엔드포인트
- 예외처리 : try-catch 직접 처리 -> @ExceptionHandler 중앙 처리

### HTTP 메서드
Get - 데이터 조회 - 게시글 목록 가져오기
Post - 데이터 생성 - 게시글 작성하기
Put - 데이터 전체 수정 - 게시글 전체 내용 교체
Patch - 데이터 일부 수정 - 게시글 제목만 수정
Delete - 데이터 삭제 - 게시글 삭제하기

> 멱등 : 같은 요청을 여러 번해도 결과가 바뀌지 않는 성질.
>> Get, Put, Delete은 멱등하다.
> Post는 멱등하지 않다. 

### HTTP 메시지 구조
1. 요청
   POST /posts HTTP/1.1                    ← Start Line (메서드 + URL + HTTP 버전)
   Content-Type: application/json          ← Header (부가 정보)
   Authorization: Bearer eyJhbGci...

{                                       ← Body (실제 전달 데이터)
"title": "오늘 학식 뭐임",
"content": "돈까스래"
}

2. Body에 담기는 데이터 형식 - JSON
   {
   "id": 1,
   "title": "오늘 학식 뭐임",
   "content": "돈까스래",
   "author": "익명"
   }

3. 응답
   HTTP/1.1 201 Created                   ← Status Line (HTTP 버전 + 상태 코드 + 메시지)
   Content-Type: application/json         ← Header

{                                      ← Body
"id": 1,
"title": "오늘 학식 뭐임",
"author": "익명"
}

### Rest API
- Client-Server 분리 - 클라이언트와 서버코드는 독립적 발전
- Stateless - 매 요청은 독립적
- Uniform Interface - 직관적으로 행위를 알 수 있어야
- Layered System - 클라이언트는 자신이 어떤 서버에 요청을 보내는 지 몰라도 된다.

### 에브리타임 화면 설계서 정보 구조도
[자유게시판 목록]
1. 상단 네비게이션 바
2. 광고 배너 (외부 광고)
3. 고정/추천 게시글 카드  >> 서버에서 가져와야 함!
4. 인기 게시글(Hot)
5. 게시글 피드 (스크롤)
- 제목, 미리보기, 에타  >> 서버에서 가져와야 함!
6. FAB '글쓰기 버튼'

[게시글 상세]
1. 상단 네비게이션 바
2. 작성자 정보 (아바타/닉네임/시간)  >> 서버에서 가져와야 함!
3. 게시글 본문 (제목+내용)  >> 서버에서 가져와야 함!
4. 액션바 (공감/댓글/스크랩)
5. 광고 배너
6. 댓글 목록 (스크롤)  >> 서버에서 가져와야 함!
- 댓글 (아바타/닉네임/내용)
- 대댓글 (들여쓰기, 화살표 표시)
7. 하단 댓글 입력바 (익명/입력/전송)

[글 쓰기]
1. 상단 바 (닫기/완료)
2. 제목 입력 필드
3. 본문 입력 필드
4. 커뮤니티 이용규칙 안내
- 규칙 전체 보기 링크
- 규칙 요약 텍스트
5. 하단 툴바
- 카메라/링크 첨부
- 질문 토글/익명 토글

### 와인잔 조 API 명세서... 끄적여보기
[수업 내용]
GET    /posts          → 게시글 목록 조회
POST   /posts          → 게시글 작성
GET    /posts/1        → 1번 게시글 조회
PUT    /posts/1        → 1번 게시글 수정
DELETE /posts/1        → 1번 게시글 삭제

get이랑 delete은 보통 Body가 없다.
URL 자체가 이미 뭘 원하는지를 말해주고 있기 때문

따라서 우리가 작성해야 할 것은?

1. 게시글 목록
2. 게시글 상세
3. 게시글 작성
4. 게시글 수정
5. 게시글 삭제

채워야 하는 것은? 
> Method / URL / Request Body / Response Body / 상태코드

### 1. 게시글 목록 - 자유게시판 목록 화면
Method : GET
URL : /posts
Request Body : 없음
Response Body: List

- id: Long
- author: String
- createdAt: LocalDateTime
- title : String
- content: String
- likeCount: int
- commentCount: int

상태 코드 : 200 OK
실패 : 없음 (빈 배열 반환)

### 2. 게시글 상세 조회 - 게시글 상세 화면
Method : GET
URL : /posts/{id}
Request Body: 없음
Response Body:

- id : Long
- author : String
- createdAt : LocalDateTime
- title : String
- content : String
- commentCount: int
- comments : List
- {id : Long, 
- author : String, 
- content : String, 
- createdAt : LocalDateTime}

상태코드 : 200 OK
실패 : 404 Not Found - 게시글 id 존재하지 않음

> 이걸 똑같이 author로 받아도 될까요...?? 뭔가 cauthor이라던지...로 나눠야 할까요?

### 3. 게시글 작성 - 글쓰기 화면
Method : POST
URL : /posts
Request Body:
- title : String
- content: String
- isAnonymous : boolean
- isQuestion : boolean
> 카메라/링크 첨부는 어떤 식으로 구현해야 할까요?
> 에타에는 질문토글 기능이 없는데, 화면 설계서에는 있어서 일단 구현해보았어요

Response Body:
- id : Long

> 게시글 작성 성공 후에 '게시글 상세'로 다시 돌아가야 한다. 그러면..? id + title + content + 첨부한 파일을 가져오면 되지 않나
> 근데 이건 찾아보니까 어차피 상세 페이지로 이동할 때 다시 상세 조회 API를 호출하므로 title, content는 response에 넣지 않아도 된다고 한다.

상태코드 : 201 Created
실패 : 400 Bad Request - title/content가 올바르지 않은 경우

> 200은 그냥 성공. 201은 "새로운 리소스가 생성됨"->게시글 작성 성공했음을 의미

### 4. 게시글 수정 - 글쓰기 수정 화면
Method : PUT
URL : /posts/{id}
Request Body:
- title : String
- content : String
- isAnonymous : boolean

Response Body: 없음

> 수정도 게시글 작성과 같은 구조라면, 굳이 나눌 필요가 없을 것 같아서 화면 설계서를 봤는데 사용자가 수정할 때의 화면설계서는 없어서
> 임의로 생각해보았어요.. 익명이랑 질문토글은 굳이 필요할까 싶다가도, 실수로 실명으로 올렸는데, 익명으로 바꿀 수 있어야 하니까 그냥 질문 토글만 뺐어요

상태코드 : 200 OK
실패 : 존재하지 않는 게시글 id의 경우 404 Not Found
title이나 content 가 올바르지 않을 경우 400 Bad Request

### 5. 게시글 삭제 - 게시글 상세 화면
Method : DELETE
URL : /posts/{id}
Request Body: 없음
Response Body: 없음

상태코드 : 204 No Content
실패 : 404 Not found - 존재하지 않는 게시글 id


## 💡 와인잔조 코드리뷰하며 배운 점

[자윤님] 내 코드는 LocalDateTime.toString() 그대로 반환하는데, 자윤님 코드는 createdAt을 사용자 친화적인 형식으로 포맷팅하는 걸 보고, 
내 코드에도 반영해봐야겠다고 생각했다. 클라이언트가 날짜를 직접 파싱하지 않아도 되니까 더 편리할 것 같다.

"post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))"


