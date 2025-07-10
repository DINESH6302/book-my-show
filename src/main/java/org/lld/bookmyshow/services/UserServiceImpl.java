package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.User;
import org.lld.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String name, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new RuntimeException("User with this email already exists");
        }
        
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }
}
