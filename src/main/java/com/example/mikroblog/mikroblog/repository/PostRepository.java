package com.example.mikroblog.mikroblog.repository;

import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.model.UserComment;
import com.example.mikroblog.mikroblog.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostRepository extends JpaRepository<UserPost, Long> {
    UserPost findByPostId(Long postId);

    @Query(value = "Select distinct * From user_post where user_user_id in (:id) order by post_create_date DESC",nativeQuery = true)
   List<UserPost> getUserPostByUserId(Long id);

    @Query(value = "Select distinct * From user where user_user_id in (:id) order by post_create_date DESC",nativeQuery = true)
    List<User> getUserWhoGivesCommentInPost(Long id);


//    @Query(value = "Select distinct post_id, user_post.post_content, showed_user_name, user_id, comment_id, user_comment.post_content, user_comment.user_user_id From user_post left join user on user_post.user_user_id=user.user_id \n" +
//            "left join user_comment on post_id=user_post_post_id where user_post.user_user_id=:id",nativeQuery = true)
//    Map<UserPost, UserComment> getUserPostAndCommentsToPostByUserId(Long id);
    //List<UserPost> getUserPostAndCommentsToPostByUserId(Long id);



}
