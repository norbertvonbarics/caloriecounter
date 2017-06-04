package com.greenfox.caloriecounter.controller;


import com.greenfox.caloriecounter.model.Meal;
import com.greenfox.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

  @Autowired
  MealRepository mealRepo;

  @RequestMapping("/getmeals")
  public Iterable<Meal> getMeals(){
    Iterable<Meal> mealIterable = mealRepo.findAll();
    return mealIterable;
  }

  @RequestMapping("getstats")
  public int getStats(){
    Iterable<Meal> mealIterable = mealRepo.findAll();
    int consumedCalories = 0;
    for(int i = 0; i  < mealRepo.count(); i++){
      consumedCalories += ((ArrayList<Meal>) mealIterable).get(i).getCalories();
    }
    return consumedCalories;
  }
}
