package com.myStudy.projectmanagement.service;

import com.myStudy.projectmanagement.config.JwtProvider;
import com.myStudy.projectmanagement.modal.User;
import com.myStudy.projectmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        try {
            String token = jwt.replace("Bearer ", "").trim(); // Ensure token is correctly formatted
            String email = JwtProvider.getEmailFromToken(token);
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new Exception("Failed to parse JWT token", e);
        }
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new Exception("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public User updateUserProjectSize(User user, int number) {
        user.setProjectSize(user.getProjectSize()+number);
        return userRepository.save(user);
    }
}
