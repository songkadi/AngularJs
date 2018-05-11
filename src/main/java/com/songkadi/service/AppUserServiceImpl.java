package com.songkadi.service;

import com.songkadi.model.AppUser;
import com.songkadi.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser findById(Long id) {
        return appUserRepository.findOne(id);
    }

    public AppUser findByName(String name) {
        return appUserRepository.findByName(name);
    }

    public AppUser saveUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser updateUser(AppUser appUser) {
        return saveUser(appUser);
    }

    public void deleteUserById(Long id) {
        appUserRepository.delete(id);
    }

    public void deleteAllUsers() {
        appUserRepository.deleteAll();
    }

    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    public boolean isUserExist(AppUser appUser) {
        return findByName(appUser.getName()) != null;
    }
}
