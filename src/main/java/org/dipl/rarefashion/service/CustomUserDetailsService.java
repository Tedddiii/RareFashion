package org.dipl.rarefashion.service;

import lombok.RequiredArgsConstructor;
import org.dipl.rarefashion.entity.User;
import org.dipl.rarefashion.model.LoggedUser;
import org.dipl.rarefashion.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        LoggedUser loggedUser = new LoggedUser(username, user.getPassword(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleId())).toList());
        loggedUser.setName(user.getName());

        return loggedUser;
    }
}
