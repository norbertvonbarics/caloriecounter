package com.greenfox.caloriecounter.model;


import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealId {

  @NotNull
  long id;

  public MealId(int id) {
    this.id = id;
  }

  public MealId() {
  }
}

