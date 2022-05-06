package org.example.repo;

import org.junit.Assert;
import org.junit.Test;

public class PersonEntityTest {

  @Test
  public void test_person_default_constructor() {
    PersonEntity testClass = new PersonEntity();

    testClass.setFirstName("Mary");
    Assert.assertEquals("Mary", testClass.getFirstName());

    testClass.setLastName("Zheng");
    Assert.assertEquals("Zheng", testClass.getLastName());
  }
}
