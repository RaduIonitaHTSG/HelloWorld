package org.example.repo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

  @Autowired private TestEntityManager entityManager;
  @Autowired private PersonRepository personDao;

  @Before
  public void setup() {
    Assert.assertNotNull(entityManager);
    Assert.assertNotNull(personDao);

    PersonEntity personEntity1 = new PersonEntity();
    personEntity1.setFirstName("Gigi");
    personEntity1.setCompanyName("HTSG");
    entityManager.persist(personEntity1);

    PersonEntity personEntity2 = new PersonEntity();
    personEntity2.setFirstName("Alex");
    personEntity2.setCompanyName("Alex company");
    entityManager.persist(personEntity2);
  }

  @Test
  public void findAll_return_list_when_found() {
    List<PersonEntity> found = personDao.findAll();

    Assert.assertNotNull(found);
    Assert.assertEquals(2, found.size());
  }

  @Test
  public void findByCompany_return_person_when_found() {
    List<PersonEntity> found = personDao.findByCompany("HTSG");

    Assert.assertNotNull(found);
    Assert.assertEquals(1, found.size());
    Assert.assertEquals("Gigi", found.get(0).getFirstName());
  }

  @Test
  public void findByCompany_return_emptylist_when_not_found() {
    List<PersonEntity> found = personDao.findByCompany("Test-notExist");

    Assert.assertNotNull(found);
    Assert.assertTrue(found.isEmpty());
  }

  @Test
  public void findOne_return_null_when_not_found() {
    Optional<PersonEntity> found = personDao.findById(-9);

    Assert.assertTrue(found.isEmpty());
  }
}
