package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.PostFrom;
import com.example.mikroblog.mikroblog.repository.PostRepository;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import com.example.mikroblog.mikroblog.service.PostService;
import com.example.mikroblog.mikroblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/")
public class IndexController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("postList",postService.sortPostByDate() );
        model.addAttribute("newCommentForm", new PostFrom());
        model.addAttribute("newPostForm", new PostFrom());
        return "index";
    }

    @GetMapping("/login/error")
    public String failureLogin(Model model) {
        String message = "Username or password is wrong";
        model.addAttribute("error", message);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

}

