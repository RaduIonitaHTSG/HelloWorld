package org.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {
  @Autowired private UserService userService;

  @Test(expected = UserNotFoundException.class)
  public void findById_throw_exception_when_not_found() {
    userService.findById(Integer.valueOf(-1));
  }

  @Test
  public void searchByCompanyName_return_empty_when_not_found() {
    List<User> found = userService.searchByCompanyName("Test");
    Assert.assertTrue(found.isEmpty());
  }

  @Test
  public void test_save_findById_searchByCompanyName() {
    User user = new User();
    user.setFirstName("Mary");
    user.setLastName("Zheng");
    user.setCompanyName("Test");
    user = userService.save(user);
    Assert.assertNotNull(user.getUserId());

    //pas1
    User foundUser = userService.findById(user.getUserId());


    Assert.assertTrue(foundUser.equals(user));

    //pas 2
    userService.deleteAll();

    List<User> found = userService.searchByCompanyName("Test");
    Assert.assertTrue(found.isEmpty());
  }
}
