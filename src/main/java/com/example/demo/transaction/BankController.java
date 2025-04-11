package com.example.demo.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {
  private final BankService bankService;

  public BankController(BankService bankService) {
    this.bankService = bankService;
  }

  @PostMapping
  public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO) {
    return bankService.execute(transferDTO);
  }
}
