package org.sopt.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    //post랑 user를 fk로 받는다.

    protected Like(){}

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }
    public Long getId() { return this.id; }
    public User getUser(){ return user; }
    public Post getPost() {return post; }
}