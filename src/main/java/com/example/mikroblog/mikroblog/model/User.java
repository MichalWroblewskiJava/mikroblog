package com.example.mikroblog.mikroblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NonNull
    @Column(unique = true)
    private String userMail;

    @NonNull
    private String userPassword;

    @NonNull
    @Column(unique = true)
    private String userName;

    private String showedUserName;

    @CreationTimestamp
    private LocalDateTime userCreateDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    //private Object userAvatar;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userPost")
    private Set<UserPost> userPostList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userComment")
    private Set<UserComment> userCommentList;

//    @Override
//    public List<AppUser> findWhoWatchThisUser() {
//        return null;
//    }
//
//    @Override
//    public List<AppUser> findWhoIsWatchingThisUser() {
//        return null;
//    }
}
