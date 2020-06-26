package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.*;
import com.example.mikroblog.mikroblog.repository.PostRepository;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import com.example.mikroblog.mikroblog.service.CommentService;
import com.example.mikroblog.mikroblog.service.PostService;
import com.example.mikroblog.mikroblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        //model.addAttribute("massage", "Correct passwords");
        return "register";
    }


    @PostMapping("/register")
    public String userRegister(User user) {
        if (user.getPassword().equals(user.getVerifyPassword())) {
            userService.register(user);
            //userService.autoLogin(user);
            //return "redirect:/user/wall?username=" + user.getUserMail();
            //return "redirect:/user/wall/" + user.getUserMail();
            return "redirect:/user/authenticate";
        } else {
            return "redirect:/user/register";
        }
    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String userRegister(@ModelAttribute("newUser") User user, HttpServletRequest request) {
//        if (user.getPassword().equals(user.getVerifyPassword())) {
//            userService.register(user);
//            userService.autoLogin(request, user);
//            //return "redirect:/user/wall?username=" + user.getUserMail();
//            return "redirect:/user/authenticate";
//        } else {
//            return "redirect:/user/register";
//        }
//    }

    @GetMapping("/authenticate")
    public String editWall(Authentication authentication) {
        String userMail = authentication.getName();
        //return "redirect:/user/wall?username=" + userMail;
        return "redirect:/user/wall/" + userMail;
    }


    //    @GetMapping("/wall")
//    public String editUserWall(Model model, @RequestParam(name = "username") String userMail) {
//        User user = userRepository.findByUserMail(userMail);
//        model.addAttribute("userWall", user);
//        model.addAttribute("newPostForm", new PostFrom());
//        model.addAttribute("newCommentForm", new UserComment());
//        //model.addAttribute("userPostList", postService.findPostOfUserByUsername(userMail));
//        //model.addAttribute("postList",postService.sortPostByDate() );
//        model.addAttribute("postList", postService.findAllPostsAndCommentsToPost());
//        return "user-wall";
//    }
    @GetMapping("/wall/{username}")
    public String editUserWall(Model model, @PathVariable(name = "username") String userMail) {
        User user = userRepository.findByUserMail(userMail);
        model.addAttribute("userWall", user);
        model.addAttribute("newPostForm", new PostFrom());
        model.addAttribute("newCommentForm", new UserComment());
        //model.addAttribute("userPostList", postService.findPostOfUserByUsername(userMail));
        //model.addAttribute("postList",postService.sortPostByDate() );
        model.addAttribute("postList", postService.findAllPostsAndCommentsToPost());
        return "user-wall";
    }
}
