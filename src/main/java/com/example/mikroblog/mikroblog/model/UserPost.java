package com.example.mikroblog.mikroblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appPostId;

    @Column(length = 160)
    private String appPostContent;

    @CreationTimestamp
    private LocalDateTime postCreateDate;

    @Enumerated(EnumType.STRING)
    private PostStatus appPostStatus;

    @Enumerated(EnumType.STRING)
    private PostTyp appPostTyp;

    @ManyToOne()
    private User userPost;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postComment")
    private List<UserComment> userCommentList;

}
