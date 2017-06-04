package com.greenfox.caloriecounter.controller;

import static org.junit.Assert.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.greenfox.caloriecounter.CaloriecounterApplication;
import com.greenfox.caloriecounter.model.Meal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaloriecounterApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class MainRestControllerTest {



    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddMeal() throws Exception {

      Meal testMeal = new Meal(new Timestamp(1322018752992L), "Lunch", "this is a test Meal", 2502);
      ObjectMapper mapper = new ObjectMapper();
      String jsonInput = mapper.writeValueAsString(testMeal);

      mockMvc.perform(post("/meal")
          .contentType(contentType)
          .content(jsonInput))
          .andExpect(status().isOk())
          .andExpect(content().contentType(contentType))
          .andExpect(jsonPath("$.status", is("ok")));
    }

  @Test
  public void testUpdateMeal() throws Exception {

    Meal testMeal = new Meal(new Timestamp(1322018752992L), "Lunch", "this is a test Meal", 2502);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(testMeal);

    mockMvc.perform(put("/meal")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }


  @Test
  public void testDeleteMeal() throws Exception {

    Meal testMeal = new Meal(new Timestamp(1322018752992L), "Lunch", "this is a test Meal", 2502);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(testMeal);

    mockMvc.perform(put("/meal")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }

  @Test
  public void getMeals() throws Exception {
  }

  @Test
  public void getStats() throws Exception {
  }

  @Test
  public void addMeal() throws Exception {
  }

  @Test
  public void updateMeal() throws Exception {
  }

  @Test
  public void deleteMeal() throws Exception {
  }

}