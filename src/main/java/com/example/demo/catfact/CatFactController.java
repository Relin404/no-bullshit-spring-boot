package com.example.demo.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catfacts")
public class CatFactController {
  private final CatFactService catFactService;

  public CatFactController(CatFactService catFactService) {
    this.catFactService = catFactService;
  }


  @GetMapping
  public ResponseEntity<CatFactDTO> getCatFact(@RequestParam(value = "max_length", defaultValue = "140") Integer max_length) {
    return catFactService.execute(max_length);
  }
}
