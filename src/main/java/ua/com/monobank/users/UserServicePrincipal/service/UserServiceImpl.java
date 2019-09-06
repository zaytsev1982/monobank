package ua.com.monobank.users.UserServicePrincipal.service;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.monobank.users.UserRepository.UserRepository;
import ua.com.monobank.users.entity.Roles;
import ua.com.monobank.users.entity.User;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User userRegister(User user) {
        User userCandidate = new User();
        userCandidate.setLogin(user.getLogin());
        userCandidate.setPassword(passwordEncoder.encode(user.getPassword()));
        userCandidate.setRoles(Collections.singleton(Roles.USER));

        User registerUser = userRepository.save(userCandidate);
        log.info("IN method userRegister, user {} register successfully", registerUser);
        return userRepository.save(registerUser);
    }
}
