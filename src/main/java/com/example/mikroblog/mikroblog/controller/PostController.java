package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.PostFrom;
import com.example.mikroblog.mikroblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/user/post")
    public String addPost(PostFrom userPost) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.add(userPost, username);
        return "redirect:/user/wall?username=" + username;
    }


}
