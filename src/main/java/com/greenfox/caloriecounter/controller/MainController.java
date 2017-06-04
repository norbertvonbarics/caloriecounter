package com.greenfox.caloriecounter.controller;

import com.greenfox.caloriecounter.model.Meal;
import com.greenfox.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  MealRepository mealRepo;


  @RequestMapping("/")
  public String mainpage(Model model) {
    model.addAttribute("mealRepo", mealRepo.findAll());
    return "index";
  }

  @RequestMapping("/addMeal")
  public String addMeal(Model model) {
    ArrayList<String> mealtype = new ArrayList<>(
        Arrays.asList("Breakfast", "Elevenses", "Lunch", "Snack", "Dinner",
            "Midnight Snack"));
    model.addAttribute("mealtype", mealtype);
    return "addMeal";
  }

  @RequestMapping(value = "/addMeal/added")
  public String addTodo(@RequestParam(name = "newMeal") String newMeal,
      @RequestParam(name = "newCalorie") String newCalorie,
      @RequestParam(name = "choosedType") String mealtype) {
    int cal = Integer.parseInt(newCalorie);
    mealRepo.save(new Meal(mealtype, newMeal, cal));
    return "redirect:/";
  }
}
