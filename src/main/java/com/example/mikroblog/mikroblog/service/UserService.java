package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.DAO.AppUserWatchDAO;
import com.example.mikroblog.mikroblog.model.AppUser;
import com.example.mikroblog.mikroblog.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;


    public void add(AppUser appUser){
        appUserRepository.save(appUser);
    }

}
