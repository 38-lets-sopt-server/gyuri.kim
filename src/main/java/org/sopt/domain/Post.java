package org.sopt.domain;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
    @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요
    private User user;

    protected Post(){}

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getContent() { return content; }
    public String getAuthor() { return user.getNickname(); }

}