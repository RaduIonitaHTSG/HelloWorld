package org.example.controller;

import org.example.service.User;
import org.example.service.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean UserServiceImpl userService;

  @Test
  public void getAllUsers_Ok() throws Exception {

    User testUser = new User();
    testUser.setFirstName("Radu");
    List<User> expectedAllUsers = Arrays.asList(testUser);

    Mockito.when(userService.findAll()).thenReturn(expectedAllUsers);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/all") // metoda apelata
                .contentType(MediaType.APPLICATION_JSON)) // formtul datelor transferate

        .andExpect(MockMvcResultMatchers.status().isOk()) // verificarea statusului
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is(testUser.getFirstName())));
  }
}
