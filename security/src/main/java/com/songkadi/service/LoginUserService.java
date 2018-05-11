package com.songkadi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginUserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
}
