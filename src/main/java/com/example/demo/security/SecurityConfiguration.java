package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable) // Disable CSRF in order to allow POST requests
      .authorizeHttpRequests(authorize -> {
        authorize.requestMatchers("/security/users").permitAll();

        authorize.anyRequest().authenticated();
      })
      .addFilterBefore(
        new BasicAuthenticationFilter(authenticationManager(httpSecurity)),
        UsernamePasswordAuthenticationFilter.class
      )
      .build();
  }
}
