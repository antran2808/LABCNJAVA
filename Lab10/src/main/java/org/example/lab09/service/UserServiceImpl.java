package org.example.lab09.service;

import jakarta.transaction.Transactional;
import org.example.lab09.model.User;
import org.example.lab09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        List<User> userList = userRepository.findByUsername(username);
        if (userList == null || userList.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return userList.get(0);
    }

}