package com.example.mikroblog.mikroblog.controller;

import com.example.mikroblog.mikroblog.model.AppUser;
import com.example.mikroblog.mikroblog.model.AppUserStatus;
import com.example.mikroblog.mikroblog.model.AppUserType;
import com.example.mikroblog.mikroblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path ="/appuser")
public class AppUserController {
    @Autowired
    private UserService appUserService;

    @GetMapping("/add")
    public String appUserAdd(Model model) {
        model.addAttribute("newAppUser", new AppUser());
        model.addAttribute("status", AppUserStatus.values());
        model.addAttribute("types", AppUserType.values());
        return "appuser-form";
    }
    @PostMapping("/add")
    public String appUserAdd(AppUser appUser){
        appUserService.add(appUser);
        return "user-wall";
    }
}
