package com.example.mikroblog.mikroblog.repository;

import com.example.mikroblog.mikroblog.model.User;

import java.util.List;

public interface UserWatchDAO {

    List<User> findWhoWatchThisUser();
    List<User> findWhoIsWatchingThisUser();

}
