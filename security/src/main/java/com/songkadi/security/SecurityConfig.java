package com.songkadi.security;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Lazy
    private AuthenticationManager authenticationManager;

    // For @RestController
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**").authorizeRequests()
                .anyRequest().authenticated()
                .and()

                .rememberMe()
                .rememberMeParameter("remember-me");
    }

    // For @Controller, Browser, MVC
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
//                .antMatchers(
//                        "/webjars/**",
//                        "/css/**",
//                        "/js/**",
//                        "/partials/**",
//                        "/h2-console/**",
//                        "/");
                .antMatchers("/h2-console/**");
    }
}
