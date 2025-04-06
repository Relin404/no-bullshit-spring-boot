package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails admin = User
      .withUsername("admin")
//      .authorities("BASIC", "SPECIAL")
      .roles("superuser")
      .password(encoder.encode("123"))
      .build();

    UserDetails user = User
      .withUsername("user")
//      .authorities("BASIC")
      .roles("basicuser")
      .password(encoder.encode("123"))
      .build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorize -> {
        authorize.anyRequest().authenticated();
        authorize.requestMatchers("/security/open").permitAll();
      })
      .httpBasic(Customizer.withDefaults())
      .build();
  }
}
