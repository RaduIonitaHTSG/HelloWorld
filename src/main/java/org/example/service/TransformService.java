package org.example.service;

import org.example.repo.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class TransformService {

  public User toUserDomain(final PersonEntity person) {
    User user = new User();
    user.setCompanyName(person.getCompanyName());
    user.setFirstName(person.getFirstName());
    user.setLastName(person.getLastName());
    user.setUserId(person.getPersonId());
    return user;
  }

  public PersonEntity toUserEntity(final User user) {
    PersonEntity person = new PersonEntity();
    person.setCompanyName(user.getCompanyName());
    person.setFirstName(user.getFirstName());
    person.setLastName(user.getLastName());
    if (user.getUserId() != null) {
      person.setPersonId(user.getUserId());
    }
    return person;
  }
}
