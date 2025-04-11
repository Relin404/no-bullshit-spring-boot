package com.example.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {
  private final CreateNewUserService createNewUserService;

  public SecurityController(CreateNewUserService createNewUserService) {
    this.createNewUserService = createNewUserService;
  }

  @PostMapping("/users")
  public ResponseEntity<String> createUser(@RequestBody CustomUser user) {
    return createNewUserService.execute(user);
  }
}
