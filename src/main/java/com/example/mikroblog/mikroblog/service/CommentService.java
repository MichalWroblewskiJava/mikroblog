package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.model.AppComment;
import com.example.mikroblog.mikroblog.repository.AppCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppCommentService {
    @Autowired
    private AppCommentRepository appCommentRepository;

    public void add(AppComment appComment){
        appCommentRepository.save(appComment);
    }
}
