package com.vega.springit.service;

import com.vega.springit.domain.User;
import com.vega.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user){
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional /*This annotation takes care of the The messages described in the comments below */
    public void saveUsers(User... users){
        //  begin transaction
        for(User user : users){
            logger.info("Saving user: " + user.getEmail());
            try {
                userRepository.save(user);
            } catch (Exception e){
                //  rollback transaction
            }
        }
        // commit Transaction
    }
}
