package com.example.demo.security;

import com.example.demo.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserService implements Command<CustomUser, String> {
  private final PasswordEncoder passwordEncoder;

  private final CustomUserRepository customUserRepository;

  public CreateNewUserService(
    PasswordEncoder passwordEncoder,
    CustomUserRepository customUserRepository
  ) {
    this.passwordEncoder = passwordEncoder;
    this.customUserRepository = customUserRepository;
  }

  @Override
  public ResponseEntity<String> execute(CustomUser input) {
    Optional<CustomUser> optionalUser = customUserRepository.findById(input.getUsername());
    if (!optionalUser.isPresent()) {
      CustomUser user = new CustomUser();
      user.setUsername(input.getUsername());
      user.setPassword(passwordEncoder.encode(input.getPassword()));
      customUserRepository.save(user);
      return ResponseEntity.ok("User created successfully");
    }

    return ResponseEntity.badRequest().body("Failure");
  }
}
