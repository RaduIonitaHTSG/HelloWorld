package org.example.service;

import org.example.repo.PersonEntity;
import org.example.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired private PersonRepository personDao;

  @Autowired private TransformService transformer;

  @Override
  public User findById(Integer personId) throws UserNotFoundException {
    Optional<PersonEntity> found = personDao.findById(personId);

    if (found == null || found.isEmpty()) {
      throw new UserNotFoundException("not found user", personId);
    }

    return transformer.toUserDomain(found.get());
  }

  @Override
  public User save(User user) {
    PersonEntity saved = personDao.save(transformer.toUserEntity(user));
    return transformer.toUserDomain(saved);
  }

  @Override
  public List<User> searchByCompanyName(String companyName) {
    List<PersonEntity> persons = personDao.findByCompany(companyName);
    List<User> users = new ArrayList<>();
    for (PersonEntity person : persons) {
      users.add(transformer.toUserDomain(person));
    }
    return users;
  }

  @Override
  public List findAll() {
    return personDao.findAll();
  }

  @Override
  public void deleteAll() {
    personDao.deleteAll();
  }
}
