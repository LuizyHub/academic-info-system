package com.cse.academicinfosystem.users.config;

import com.cse.academicinfosystem.users.domain.Authority;
import com.cse.academicinfosystem.users.repository.AuthorityRepository;
import com.cse.academicinfosystem.users.domain.User;
import com.cse.academicinfosystem.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Authority authority = authorityRepository.findByUser(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomDetails(user, authority);
    }
}