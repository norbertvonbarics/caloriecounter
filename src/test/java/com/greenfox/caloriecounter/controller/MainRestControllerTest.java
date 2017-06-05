package com.greenfox.caloriecounter.controller;

import static org.junit.Assert.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.greenfox.caloriecounter.CaloriecounterApplication;
import com.greenfox.caloriecounter.model.Meal;
import com.greenfox.caloriecounter.model.MealId;
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

    Meal testMeal = new Meal("2011-11-11", "Lunch", "this is a test Meal", 2502);
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

    Meal testMeal = new Meal(7, "2011-11-11", "Lunch", "this is a test Meal", 2502);
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

    MealId testMealId = new MealId(7);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(testMealId);
    System.out.println(jsonInput);

    mockMvc.perform(delete("/meal")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }

  @Test
  public void testAddMeal_withShittyInput() throws Exception {

    mockMvc.perform(post("/meal")
        .contentType(contentType)
        .content("{\n"
            + "    \"date\": 1496585531246,\n"
            + "    \"description\": \"this is a big happy meal, hope with the good id\",\n"
            + "    \"calories\": 2\n"
            + "  }"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.error", is("Missing field(s): type, ")));
  }
}
