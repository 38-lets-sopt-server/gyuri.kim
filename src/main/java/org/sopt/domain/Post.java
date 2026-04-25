package org.sopt.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    //private String author;
    //private String createdAt;

    @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
    @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요
    private User user;

    protected Post(){}

    public Post(String title, String content, String author, String createdAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public String getCreatedAt() { return createdAt; }


    public String getInfo() {
        return "[" + id + "] " + title + " - " + author + " (" + createdAt + ")\n" + content;
    }
}