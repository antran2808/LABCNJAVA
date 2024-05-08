package org.example.lab09.service;

import org.example.lab09.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User update(User user);

    public User loadUserByUsername(String username) throws UsernameNotFoundException;
}
