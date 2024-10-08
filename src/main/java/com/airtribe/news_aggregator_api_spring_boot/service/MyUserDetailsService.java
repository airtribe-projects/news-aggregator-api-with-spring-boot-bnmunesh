package com.airtribe.news_aggregator_api_spring_boot.service;

import com.airtribe.news_aggregator_api_spring_boot.model.UserPrincipal;
import com.airtribe.news_aggregator_api_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return _userRepository.findByEmail(email)
                .map(UserPrincipal::new)
                .orElseThrow(() -> {
                    System.out.println("User not found: "+email);
                    return new UsernameNotFoundException("User not found with email: " +email);
                });
    }
}
