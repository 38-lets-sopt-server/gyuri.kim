package org.sopt.domain;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE post SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
    @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();  // User : Post = N : 1 !

    @Column(name = "deleted_at") //소프트 딜리트 구현!
    private LocalDateTime deletedAt;

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
    public User getUser() { return user; }
    public List<Like> getLikes() { return likes; }
    //이 author이랑 nickname이랑 꼬여서.. 어디서부터 수정해야 할지 모르겠어요
    public LocalDateTime getDeletedAt(){ return deletedAt;}

}