package org.example.service;

import org.example.repo.PersonEntity;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransformServiceTest {

  private TransformService testClass = new TransformService();

  @Test
  public void test_toDomain() {
    PersonEntity person = new PersonEntity();
    person.setCompanyName("test company");
    person.setFirstName("Mary");
    person.setLastName("Zheng");
    person.setPersonId(1);
    User user = testClass.toUserDomain(person);

    assertNotNull(user);
    assertEquals("test company", user.getCompanyName());
    assertEquals("Mary", user.getFirstName());
    assertEquals("Zheng", user.getLastName());
    assertEquals(1, user.getUserId().intValue());
  }

  @Test
  public void test_toEntity() {
    User user = new User();

    user.setCompanyName("test company");
    user.setFirstName("Mary");
    user.setLastName("Zheng");
    user.setUserId(Integer.valueOf(1));

    PersonEntity person = testClass.toUserEntity(user);

    assertNotNull(user);
    assertEquals("test company", person.getCompanyName());
    assertEquals("Mary", person.getFirstName());
    assertEquals("Zheng", person.getLastName());
    assertEquals(1, person.getPersonId());
  }
}
