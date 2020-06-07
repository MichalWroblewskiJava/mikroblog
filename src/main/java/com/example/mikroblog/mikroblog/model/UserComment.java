package com.example.mikroblog.mikroblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appCommentId;

//    private Long postUserId = getAppPostComment().getAppUserPost().getAppUserId();
//    private Long commentUserId = getAppUserComment().getAppUserId();


    @ManyToOne
    private UserPost postComment;

    @ManyToOne()
    private User userComment;

//    public Comment(Post appPostComment, Long postUserId, Long commentUserId) {
//        this.appPostComment = appPostComment;
//        this.postUserId = postUserId;
//        this.commentUserId = commentUserId;
//    }
}
