package com.greenfox.caloriecounter.controller;

import com.greenfox.caloriecounter.model.Meal;
import com.greenfox.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.text.html.HTMLDocument.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;

@Controller
public class MainController {

  @Autowired
  MealRepository mealRepo;


  @RequestMapping("/")
  public String mainpage(Model model) {
    Iterable<Meal> mealIterable = mealRepo.findAll();
    int consumedCalories = 0;
    for(int i = 0; i  < mealRepo.count(); i++){
      consumedCalories += ((ArrayList<Meal>) mealIterable).get(i).getCalories();
    }

    model.addAttribute("mealRepo", mealRepo.findAll());
    model.addAttribute("countMeals", mealRepo.count());
    model.addAttribute("countCalories", consumedCalories);

    return "index";
  }

  @RequestMapping("/addMeal")
  public String addMeal(Model model) {
    ArrayList<String> mealtype = new ArrayList<>(
        Arrays.asList("Breakfast", "Elevenses", "Lunch", "Snack", "Dinner",
            "Midnight Snack"));
    model.addAttribute("mealtype", mealtype);
    model.addAttribute("mealRepo", mealRepo.findAll());
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

  @RequestMapping(value = "/deleteMeal")
  public String deleteTodo(@RequestParam(name = "delete") long id) {
    mealRepo.delete(id);
    return "redirect:/";
  }

  @RequestMapping(value = "/{id}/edit")
  public String edit(Model model, @PathVariable("id") Long id) {
    ArrayList<String> mealtype = new ArrayList<>(
        Arrays.asList("Breakfast", "Elevenses", "Lunch", "Snack", "Dinner",
            "Midnight Snack"));
    model.addAttribute("mealtype", mealtype);

    model.addAttribute("meal", mealRepo.findOne(id));
    return "editMeal";
  }

  @RequestMapping(value = "/{id}/edit/update")
  public String updated(@PathVariable("id") Long id, @RequestParam("description") String description,
      @RequestParam(value = "calories") int calories,
      @RequestParam(value = "choosedType") String choosedType) {

    Meal meal = mealRepo.findOne(id);
    meal.setDescription(description);
    meal.setCalories(calories);
    meal.setType(choosedType);

    mealRepo.save(meal);

    return "redirect:/";
  }
}
