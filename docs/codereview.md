## 1주차 과제 코드리뷰
### 규일님의 코드를 보고..
나는 DTO를 class로 작성했는데, record를 활용하면 생성자와 getter가 자동 생성되어 코드가 훨씬 간결해진다는 점을 배웠다. 

또한 createdAt을 String으로 변환하지 않고 LocalDateTime 타입 그대로 유지하면 날짜 계산이나 비교 시 훨씬 편리하다는 점도 인상 깊었다.

예외 처리에 있어서도 나는 PostNotFoundException과 IllegalArgumentException을 각각 잡았는데, 
규일님께서는 RuntimeException으로 한 번에 잡아 코드를 간결하게 작성하셨다. 각각 잡는 방식은 예외마다 다른 처리가 가능하다는 장점이 있지만, 
지금처럼 단순한 구조에서는 RuntimeException으로 한 번에 처리하는 방식이 더 깔끔하다고 느꼈다.

기능 구현에만 집중하다 보니 코드가 길어졌는데, 앞으로는 클린코드를 고려하며 작성하는 습관을 길러야겠다고 생각했다.


### 채현님의 코드를 보고..
나는 CreatePostRequest.java에서 그냥 String title, String content 이런식으로 했는데, 채현님은 private으로 캡슐화를 구현하셨다.
private + getter → 캡슐화로 데이터 보호를 하는 코드였다..! 적용해봐야겠다.
더 간결하게 하려면 → record 사용 가능하다는 점은 규일님의 코드를 보고 생각해냈던 보완점이었는데 (코드가 너무 길어져서) 규일님이 이미 리뷰로 남기셨다..ㅎㅎ

### 성휘님의 코드를 보고..
나는 모든 메서드에서 IllegalArgumentException과 PostNotFoundException을 일괄적으로 처리했는데, 성휘님께서 createPost, getPost, updatePost, deletePost마다 
발생할 수 있는 예외를 고려해 각각 다르게 처리하신 것을 보고 많이 배웠다! 
각 메서드가 하는 일이 다르기 때문에 터질 수 있는 예외도 다르다는 점을 간과하고 있었는데, 앞으로는 메서드의 역할을 정확히 파악하고 그에 맞는 예외만 처리하는 습관을 길러야겠다고 느꼈다. 
추가로 PostNotFoundException의 파라미터를 String으로 받으면 매번 메시지를 직접 작성해야 해서 일관성이 떨어질 수 있는데, 
Long id를 파라미터로 받아 메시지를 자동 생성하는 방식도 고려해보면 좋을 것 같다고 생각했다.

### 자윤님의 코드를 보고..
나는 for문으로 게시글 목록을 변환했는데, 자윤님께서 stream()으로 흐름을 만들고 map()으로 각 Post를 변환한 뒤 toList()로 모으는 방식을 사용하신 것을 보고 훨씬 간결하다는 것을 느꼈다! 
Java stream을 처음 접해봤는데, 다음 과제에서는 꼭 적용해보고 싶었다.

피드백을 드렸던 내용은, post==null일 때 IllegalArgumentException을 던지는 코드가 있었는데, 
이는 입력값이 잘못됐을 때 사용하는 예외...이다. 여기서는 입력값이 잘못된 것이 아니라 ID에 해당하는 게시글이 존재하지 않는 상황이므로, RuntimeException을 상속받은 PostNotFoundException을 사용하는 것이 의미상 더 적절할 것 같다고 피드백을 드렸다!