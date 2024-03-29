package com.vega.springit.service;

import com.vega.springit.domain.User;
import com.vega.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final MailService mailService;

    public UserService(UserRepository userRepository, RoleService roleService, MailService mailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        encoder = new BCryptPasswordEncoder();
        this.mailService = mailService;
    }

    public User register(User user) {
        //Take the password from the form and encode it
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);

        //Confirm Password
        user.setConfirmPassword(secret);

        //Assign a Role to this user
        user.addRole(roleService.findByName("ROLE_USER"));

        //Set an activation code
        user.setActivationCode(UUID.randomUUID().toString());

        //Disable the user
        user.setEnabled(false);

        //save user
        save(user);

        //send the activation email
        sendActivationEmail(user);

        //return the user
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional /*This annotation takes care of the The messages described in the comments below */
    public void saveUsers(User... users) {
        //  begin transaction
        for (User user : users) {
            logger.info("Saving user: " + user.getEmail());
            try {
                userRepository.save(user);
            } catch (Exception e) {
                //  rollback transaction
            }
        }
        // commit Transaction
    }

    public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        mailService.sendWelcomeEmail(user);
    }

    public Optional<User> findByEmailAndActivationCode(String email, String activationCode){
        return userRepository.findByEmailAndActivationCode(email, activationCode);
    }
}
