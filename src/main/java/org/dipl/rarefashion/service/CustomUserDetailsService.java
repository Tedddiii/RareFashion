package org.dipl.rarefashion.service;

import lombok.RequiredArgsConstructor;
import org.dipl.rarefashion.entity.User;
import org.dipl.rarefashion.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(r -> r.getRoleId()).toArray(String[]::new))
                .build();

        return userDetails;
    }
}
