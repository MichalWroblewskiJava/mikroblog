package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.model.PostFrom;
import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.model.UserComment;
import com.example.mikroblog.mikroblog.model.UserPost;
import com.example.mikroblog.mikroblog.repository.CommentRepository;
import com.example.mikroblog.mikroblog.repository.PostRepository;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public void addComment(PostFrom postFrom, String username, Long postId) {
        UserComment userComment = new UserComment();
        userComment.setPostFrom(postFrom);
        UserPost byPostId = postRepository.findByPostId(postId);
        userComment.setUserPost(byPostId);
        User user = userRepository.findByUserMail(username);
        userComment.setUser(user);
        userComment.setShowedUserName(user.getShowedUserName());
        commentRepository.save(userComment);

    }

    public List<UserComment> findCommentsToPostByPostId(Long postId) {
        List<UserComment> commentsToPostList = new ArrayList<UserComment>(postRepository.findByPostId(postId).getUserCommentList());
        commentsToPostList.sort((o1, o2) -> o2.getCommentCreateDate().compareTo(o1.getCommentCreateDate()));
        return commentsToPostList;
    }

    public List<UserComment> sortSetOfCommentsByDate(Set<UserComment> commentsToPostSet) {
        List<UserComment> commentsToPostList = new ArrayList<UserComment>(commentsToPostSet);
        commentsToPostList.sort((o1, o2) -> o2.getCommentCreateDate().compareTo(o1.getCommentCreateDate()));
        return commentsToPostList;
    }
}