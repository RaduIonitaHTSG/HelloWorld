package org.example.service;

import org.example.repo.PersonEntity;
import org.example.repo.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MockUserServiceImplTest {

  private static final String MARY = "Mary";
  private static final String TEST_COMPANY = "Test";

  @InjectMocks private UserServiceImpl userServiceImpl; // serviciu ce se doreste a fi testat
  @Mock private PersonRepository personDao; // dependinta a serviciului
  @Mock private TransformService transformer; // dependinta a serviciului

  private PersonEntity expectedPerson = new PersonEntity();
  private User expectedUser = new User();

  @Before
  public void setup() {
    expectedPerson.setFirstName(MARY);
    expectedUser.setFirstName(MARY);
  }

  @Test
  public void findById_found() {
    Mockito.doReturn(Optional.of(expectedPerson)).when(personDao).findById(Integer.valueOf(1));

    Mockito.doReturn(expectedUser).when(transformer).toUserDomain(expectedPerson);

    User user = userServiceImpl.findById(Integer.valueOf(1));
    Assert.assertEquals(MARY, user.getFirstName());
  }

  @Test(expected = UserNotFoundException.class)
  public void findById_not_found() {
    Mockito.doReturn(null).when(personDao).findById(Integer.valueOf(1));

    userServiceImpl.findById(Integer.valueOf(1));
  }

  @Test
  public void searchByCompanyName_found() {
    List<PersonEntity> persons = new ArrayList<>();
    persons.add(expectedPerson);

    Mockito.doReturn(persons).when(personDao).findByCompany(TEST_COMPANY);
    Mockito.doReturn(expectedUser).when(transformer).toUserDomain(expectedPerson);

    List<User> users = userServiceImpl.searchByCompanyName(TEST_COMPANY);

    Assert.assertEquals(1, users.size());
    Assert.assertEquals(MARY, users.get(0).getFirstName());
  }

  @Test
  public void searchByCompanyName_not_found() {
    List<PersonEntity> persons = new ArrayList<>();
    Mockito.doReturn(persons).when(personDao).findByCompany(TEST_COMPANY);

    List<User> users = userServiceImpl.searchByCompanyName(TEST_COMPANY);
    Assert.assertTrue(users.isEmpty());
  }
}
