package com.example.demo.mappings;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany
  @JoinTable(
          name = "customer_address",
          joinColumns = @JoinColumn(name = "customer_id"),
          inverseJoinColumns = @JoinColumn(name = "address_id")
  )
  private List<Address> addresses;
}
