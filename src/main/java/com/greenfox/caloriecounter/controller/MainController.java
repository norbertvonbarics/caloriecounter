package com.greenfox.caloriecounter.controller;

import com.greenfox.caloriecounter.repository.MealRepository;
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
    model.addAttribute(mealRepo.findAll());
    return "index";
  }
}
