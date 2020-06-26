package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.PostFrom;
import com.example.mikroblog.mikroblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment/{postId}")
    public String addComment(PostFrom userComment, @PathVariable(name = "postId") Long postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        commentService.addComment(userComment, username, postId);
        //return "redirect:/user/wall?username=" + username; // poprawić trzeba przenoszenie do strony
        return "redirect:/user/wall/" + username; // poprawić trzeba przenoszenie do strony
    }
    @GetMapping("/comment/{postId}")
    public Long getPostId( @PathVariable(name = "postId") Long postId) {
        return postId;
    }
}
