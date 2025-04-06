package com.example.demo.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "custom_user")
public class CustomUser {
  @Id
  @Column
  private String username;

  @Column
  private String password;
}


