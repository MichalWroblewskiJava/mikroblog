package com.example.mikroblog.mikroblog.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_post")
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;

    @Column(length = 160, name = "post_form")
    @Embedded
    PostFrom postFrom;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "post_create_date")
    private LocalDateTime postCreateDate;

    @ManyToOne()
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userPost")
    private Set<UserComment> userCommentList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPost userPost = (UserPost) o;
        return Objects.equals(postId, userPost.postId) &&
                Objects.equals(postFrom, userPost.postFrom) &&
                Objects.equals(postCreateDate, userPost.postCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postFrom, postCreateDate);
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "postId=" + postId +
                '}';
    }
}
