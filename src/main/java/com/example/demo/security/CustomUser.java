package com.example.demo.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "custom_user")
public class CustomUser {
  @Id
  @Column(name = "username")
  @NotNull
  private String username;

  @Column(name = "password")
  @NotNull
  private String password;
}


