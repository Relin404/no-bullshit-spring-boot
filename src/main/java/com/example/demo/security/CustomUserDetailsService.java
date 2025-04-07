package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
  private final CustomUserRepository customUserRepository;

  public CustomUserDetailsService(CustomUserRepository customUserRepository) {
    this.customUserRepository = customUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomUser customUser = customUserRepository.findById(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
    return User
      .withUsername(customUser.getUsername())
      .password(customUser.getPassword())
      .build();
  }
}
