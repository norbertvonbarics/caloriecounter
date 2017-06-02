package com.greenfox.caloriecounter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping("/")
  public String mainpage(){
    return "index";
  }
}
