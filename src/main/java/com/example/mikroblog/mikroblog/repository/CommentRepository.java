package com.example.mikroblog.mikroblog.repository;

import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.model.UserComment;
import com.example.mikroblog.mikroblog.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<UserComment, Long> {

//    @Query(value = "SELECT distinct showed_user_name From user_comment left join user on user_user_id=user_id where comment_id = :id",nativeQuery = true)
//    String GetShowedUserNameByCommentId(Long id);

    @Query(value = "Select * From user_comment where user_post_post_id in (:id) order  by comment_create_date DESC", nativeQuery= true)
    List<UserComment> getCommentsToPostByPostId(Long id);

}