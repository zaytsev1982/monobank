package ua.com.monobank.users.UserServicePrincipal;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.monobank.users.UserRepository.UserRepository;
import ua.com.monobank.users.entity.User;
import ua.com.monobank.users.principal.UserPrincipal;

public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceDetailsImpl(
        UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
            .orElseThrow(
                () -> new EntityNotFoundException("Cannot found user by login [" + login + "]"));
        return new UserPrincipal(user);
    }
}
