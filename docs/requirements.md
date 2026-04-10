### post의 구조
- Long id
- String title
- String content
- String author
- String createdAt

### 공통 응답 DTO가 담아야 할 것
- 성공 여부 --> boolean
- 무슨 데이터에서 발생하는 성공/실패인지 --> 타입을 어떻게 설정..? --> 제너릭..! List<>!
- 메시지 (성공/에러 종류 출력) --> String