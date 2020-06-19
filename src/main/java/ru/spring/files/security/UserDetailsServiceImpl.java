package ru.spring.files.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.files.model.User;
import ru.spring.files.repositories.UserRepositories;

import java.util.Optional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.getUserByLogin(email);
        if (user.isPresent()) {
            User user1 = user.get();
            return new UserDetailsImpl(user1);
        } throw new UsernameNotFoundException("User not found");
    }
}
