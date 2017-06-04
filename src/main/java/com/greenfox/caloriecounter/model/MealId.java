package com.greenfox.caloriecounter.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealId {
  long id;

  public MealId(int id) {
    this.id = id;
  }

  public MealId() {
  }
}

