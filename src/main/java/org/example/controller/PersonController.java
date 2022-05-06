package org.example.controller;

import org.example.service.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {

  @Autowired UserService userService;

  @GetMapping("/all")
  public List<User> getAllPersons() {
    return userService.findAll();
  }
}
