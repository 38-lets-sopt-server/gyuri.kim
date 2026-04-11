package org.sopt.dto.response;
//id랑 message만 받을 수 있는 CreatePostResponse와 달리, 사용자가 게시글을 수정하고 작성하고 반환하려면
//id, title, content, author, createdat을 받을 수 있어야 한다고 생각이 들어서
//postresponse를 만들어 보았습니다.
public class PostResponse {
    public Long id;
    public String title;
    public String content;
    public String author;
    public String created_at;

    public PostResponse(Long id, String title, String content, String author, String CreatedAt){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.created_at = created_at;
    }
}
