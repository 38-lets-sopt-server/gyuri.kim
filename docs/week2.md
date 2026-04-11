## 2주차 세미나(이론) 내용 정리
### 유효성 검증
- Controller
- Service

(domain에서 던지는 것과 service에서 던지는 것의 차이를 알아보자.)

### 순차지향 -> 절차지향 -> 객체지향

- "객체 = 책임을 가지고 특정 역할을 수행하는 독립적인 단위"

- PostService - CRUD 로직 처리 - 게시글 생성 로직
- Post - 게시글 데이터 보관 책임 - 게시글 데이터 구조 변경

- 객체지향의 필수조건 -> OOP (캡슐화, 추상화, 상속, 다형성)

- SOLID -> 선택사항

- elseif의 반복은 CPU를 낭비한다. -> 추상화를 적용하면? 문제 해결!

### 다형성
1. 오버로딩 - 같은 이름, 다른 매개변수의 메서드 여러 개
2. 오버라이딩 - 부모 메서드를 자식 클래스가 재정의

### SOLID 원칙
- S - SRP - 단일 책임 원칙 (클래스는 하나의 일)
- O - OCP - 개방-폐쇄 원칙 (기존 코드 수정x, 새 기능 확장)
- L - LSP - 부모 타입은 언제나 자식으로 교체 가능해야 함 (리스코프 치환 원칙)
- I - ISP - (인터페이스 분리 원칙)거대한 인터페이스보다 작은 인터페이스 여럿이 낫다
- D - DIP - (의존 역전 원칙) 구체 클래스가 아닌 추상화에 의존

- (컨트롤러에서 각각의 인터페이스 만들고, 이를 상속해서 기능 구현하도록 할 수도 있음)

### HTTP의 핵심 특성
- 비연결성 - 서버는 클라이언트와 계속 연결을 유지하지는 않는다
- 무상태성 - 서버는 이전 요청을 기억하지 않는다.
- 로그인 상태 유지 같은 것은 JWT, 세션 같은 별도 방식으로 해결한다.


### 멱등성
- 같은 요청을 여러 번 해도 결과가 변하지 않는 성질
- (POST)는 멱등하지 않다.

### RestAPI - 자원 중심 설계
(URL, URI 차이점 알아보기)
- Client, Server 분리
- Stateless : 매 요청은 독립적
- Uniform Interface
- Layered System

(스프링부트 실행 시, 저절로 뜨는 로그들을 공부해보면, 심화된 공부를 할 수 있을 것!)

### IoC/DI
-Spring은 IoC (제어의 역전) + DI (의존성 주입)

### Bean -> Spring이 관리하는 객체
- 어노테이션을 붙이면, Spring이 알아서 클래스를 Bean으로 등록하고 관리해줌


### 의존성 주입 방식
1. 생성자 주입
- final 로 불변성 보장
- 순환 참조를 컴파일 시점에 감지 (필드 주입은 앱이 실행될때까지 모르다가 런타임에 터진다)
- 테스트 용이성 (Spring 없이도 테스트 가능)

2. 필드 주입
3. Setter 주입

### DTO의 역할 (Data Transfer Object)
- 클라이언트가 서버 내부 구조를 몰라야 한다.
- 클라이언트가 서버가 알아야 할 값을 마음대로 설정할 수 없어야 한다.
: DTO는 계층 간 데이터를 명시적으로 정의하는 계약

### DTO -> POST 변환
1) 만약 COntroller에서 변환
- Service가 Post 도메인 객체에만 의존하게 되어 재사용성 높아짐
2) Service에서 반환
- id 발급이 Service->Repository 통해 이뤄지기 때문에, 추가로직이 필요한 경우 Service에서 변환하는 게 자연스럽다.


- Spring REST API에서는 ResponseEntity를 써서 HTTP 상태 코드까지 함께 내려준다.
- 클라이언트는 상태 코드를 보고 성공 방법과 여부를 파악
- Response 객체 + 상태코드 반환해야 한다.

### 자바 예외 계층 구조
1. Checked Exception
- try-catch로 감싸거나 throws로 선언하는 데에 이상 생길 시 컴파일 불가
- 파일, 네트워크, DB접근 등 외부 요인으로 인한 실패에 사용

2. Unchecked Exception
- 컴파일러가 강제 x, 논리적 오류를 표현할 때 사용

### 커스텀 예외 만들기
-> 심화 과제1

### ExceptionHandler
-> 내가 1차 과제에서 느꼈던 불편함..과 그 해결책인 핸들러!
try catch 말고, Spring에서는 @RestControllerAdvice 제공 -> 모든 Controller에서 발생하는 예외를 한 곳에서 처리


PostService에서 PostNotFoundException 던짐
↓
Controller는 잡지 않고 위로 올림
↓
Spring이 GlobalExceptionHandler로 전달
↓
handlePostNotFound() 메서드가 실행됨
↓
404 + 에러 메시지를 클라이언트에게 반환


### 1차 세미나에서 바뀐 점
- 입력 방식 : Scanner -> HTTP 요청 (@RequestBody, @PathVariable)
- 출력 방식 : System.out.println -> HTTP 응답 (ResponseEntity)
- 의존성 관리 : new로 직접 생성 -> Spring DI (@Service, @Repository)
- 통신 형식 : 훔수 직접 호출 -> REST API 엔드포인트
- 예외처리 : try-catch 직접 처리 -> @ExceptionHandler 중앙 처리

