package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.*;
import com.example.mikroblog.mikroblog.repository.PostRepository;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import com.example.mikroblog.mikroblog.service.CommentService;
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
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(User user) {
        if (user.getPassword().equals(user.getVerifyPassword())) {
            userService.register(user);
            return "redirect:/user/wall?username=" + user.getUserMail();
        } else {
            return "redirect:/user/register";
        }
    }

    @GetMapping("/authenticate")
    public String editWall(Authentication authentication) {
        String userMail = authentication.getName();
        return "redirect:/user/wall?username=" + userMail;
    }

    @GetMapping("/wall")
    public String editUserWall(Model model, @RequestParam(name = "username") String userMail) {
        User user = userRepository.findByUserMail(userMail);
        model.addAttribute("userWall", user);
        model.addAttribute("newPostForm", new PostFrom());
        model.addAttribute("newCommentForm", new UserComment());
        //model.addAttribute("userPostList", postService.findPostOfUserByUsername(userMail));
        model.addAttribute("postList",postService.sortPostByDate() );
        return "user-wall";
    }
}
