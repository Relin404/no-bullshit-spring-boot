package com.example.demo.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {
  @GetMapping("/open")
  public String open() {
    return "This is an open endpoint";
  }

  @GetMapping("/closed")
  public String closed() {
    return "This is a closed endpoint";
  }

  @PreAuthorize("hasRole('superuser')")
  @GetMapping("/special")
  public String special() {
    return "This is a special endpoint";
  }


  @PreAuthorize("hasRole('superuser') or hasRole('basicuser')")
  @GetMapping("/basic")
  public String basic() {
    return "This is a basic endpoint";
  }
}
