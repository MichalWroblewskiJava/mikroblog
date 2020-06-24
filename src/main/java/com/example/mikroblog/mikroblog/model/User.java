package com.example.mikroblog.mikroblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@SecondaryTable(
        name = "users",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))

@SecondaryTable(
        name = "authorities",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))


public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NonNull
    @Column(table = "users", name = "username", unique = true)
    private String userMail;

    @NonNull
    @Column(table = "users", name = "password")
    private String password;

    @Transient // nie jest widoczny w bazie danych
    private String verifyPassword;

    @NonNull
    @Column(table = "users", name = "enabled")
    private Boolean enabled;

    @NonNull
    @Column(table = "authorities", name = "authority")
    private String authority;

    @NonNull
    @Column(table = "authorities", name = "username")
    private String authorityUserName;

    @NonNull
    @Column( name = "user_hidden_name")
    private String userHiddenName;

    @Column( name = "showed_user_name")
    private String showedUserName;

    @CreationTimestamp
    @Column( name = "user_create_date")
    private LocalDateTime userCreateDate;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserPost> userPostList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserComment> userCommentList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userMail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                '}';
    }
}
