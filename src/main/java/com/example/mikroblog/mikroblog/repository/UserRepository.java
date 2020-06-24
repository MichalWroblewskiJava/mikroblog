package com.example.mikroblog.mikroblog.repository;

import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserMail(String userMail);

    @Query(value = "Select user_id, showed_user_name, user_create_date, user_hidden_name, user_type From user " +
            "left join user_comment on user_id=user_user_id where user_comment.comment_id=:id", nativeQuery = true)
    User getUserByCommentId(Long id);
}
