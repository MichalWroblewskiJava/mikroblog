package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.model.PostFrom;
import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.model.UserComment;
import com.example.mikroblog.mikroblog.model.UserPost;
import com.example.mikroblog.mikroblog.repository.CommentRepository;
import com.example.mikroblog.mikroblog.repository.PostRepository;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void add(PostFrom post, String username) {
        UserPost userPost = new UserPost();
        userPost.setPostFrom(post);
        User byUserMail = userRepository.findByUserMail(username);
        userPost.setUser(byUserMail);

        postRepository.save(userPost);
    }

    public List<UserPost> sortPostByDate() {
        List<UserPost> sortedPostList = new ArrayList<UserPost>(postRepository.findAll());
        sortedPostList.sort((o1, o2) -> o2.getPostCreateDate().compareTo(o1.getPostCreateDate()));
        return sortedPostList;
    }

    public List<UserPost> findPostOfUserByUsername(String username) {
        Long userId = userRepository.findByUserMail(username).getUserId();
        List<UserPost> userPostList = postRepository.getUserPostByUserId(userId);
        return userPostList;
    }

}
