package com.example.mikroblog.mikroblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_comment")
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(length = 160, name = "post_form")
    @Embedded
    PostFrom postFrom;

    @CreationTimestamp
    @Column(name = "comment_create_date")
    private LocalDateTime commentCreateDate;


    @Column(name = "showed_user_name")
    String showedUserName;

    @ManyToOne
    private UserPost userPost;

    @ManyToOne()
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComment that = (UserComment) o;
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "commentId=" + commentId +
                '}';
    }
//    }
}
