package com.greenfox.caloriecounter.controller;

import com.greenfox.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  MealRepository mealRepo;

  @RequestMapping("/")
  public String mainpage(Model model){
    model.addAttribute("mealRepo", mealRepo.findAll());
    return "index";
  }

  @RequestMapping("addMeal")
  public String addMeal(Model model){
    ArrayList<String> mealtype = new ArrayList<>(Arrays.asList("Breakfast", "Elevenses", "Lunch", "Snack", "Dinner",
        "Midnight Snack"));
    model.addAttribute("mealtype", mealtype);
    return "/addMeal";
  }


}
