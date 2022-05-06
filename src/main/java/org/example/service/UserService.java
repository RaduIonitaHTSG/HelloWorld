package org.example.service;

import java.util.List;

public interface UserService {

  User findById(Integer personId);

  User save(User user);

  List searchByCompanyName(String companyName) throws UserNotFoundException;

  List findAll();

  void deleteAll();
}
