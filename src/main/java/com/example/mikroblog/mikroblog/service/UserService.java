package com.example.mikroblog.mikroblog.service;

import com.example.mikroblog.mikroblog.model.User;
import com.example.mikroblog.mikroblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void register(User user) {
        user.setAuthority("USER");
        user.setAuthorityUserName(user.getUsername());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    //    public boolean autoLogin(HttpServletRequest request, User user) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserMail(), user.getPassword());
//        authToken.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = authenticationManager.authenticate(authToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return true;
//    }
    public void autoLogin(User user) {
        UsernamePasswordAuthenticationToken authenticationTokenToken = new UsernamePasswordAuthenticationToken(user.getUserMail(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationTokenToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

    }
}
