package com.songkadi.service;

import com.songkadi.model.LoginUser;
import com.songkadi.repositories.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserRepository.findByUsername(username);
        if (loginUser == null) {
            return new User(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<GrantedAuthority>());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
