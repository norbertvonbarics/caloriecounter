package com.greenfox.caloriecounter.controller;


import com.greenfox.caloriecounter.model.Meal;
import com.greenfox.caloriecounter.model.MealId;
import com.greenfox.caloriecounter.model.Response;
import com.greenfox.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

  @Autowired
  MealRepository mealRepo;

  @RequestMapping("/getmeals")
  public Iterable<Meal> getMeals() {
    Iterable<Meal> mealIterable = mealRepo.findAll();
    return mealIterable;
  }

  @RequestMapping("getstats")
  public int getStats() {
    Iterable<Meal> mealIterable = mealRepo.findAll();
    int consumedCalories = 0;
    for (int i = 0; i < mealRepo.count(); i++) {
      consumedCalories += ((ArrayList<Meal>) mealIterable).get(i).getCalories();
    }
    return consumedCalories;
  }

  @RequestMapping(value = "/meal", method = RequestMethod.POST)
  public Response addMeal(@RequestBody Meal receivedMeal) {
    Meal received = new Meal(receivedMeal.getId(), receivedMeal.getDate(), receivedMeal.getType(),
        receivedMeal.getDescription(), receivedMeal.getCalories());
    mealRepo.save(received);

    return new Response("ok");
  }

  @RequestMapping(value = "/meal", method = RequestMethod.PUT)
  public Response updateMeal(@RequestBody Meal receivedMeal) {
    Meal oldMeal =  mealRepo.findOne(receivedMeal.getId());
    oldMeal.setType(receivedMeal.getType());
    oldMeal.setCalories(receivedMeal.getCalories());
    oldMeal.setDescription(receivedMeal.getDescription());

    mealRepo.save(oldMeal);

    return new Response("ok");
  }

  @RequestMapping(value = "/meal", method = RequestMethod.DELETE)
  public Response deleteMeal(@RequestBody MealId mealID) {
    mealRepo.delete(mealID.getId());

    return new Response("ok");
  }
}