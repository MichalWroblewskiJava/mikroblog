package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.model.AppPost;
import com.example.mikroblog.mikroblog.repository.AppPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppPostService {
    @Autowired
    private AppPostRepository appPostService;

    public void add(AppPost appPost){
        appPostService.save(appPost);
    }
}
