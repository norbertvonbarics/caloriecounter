package com.greenfox.caloriecounter.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  Timestamp date;
  String type;
  String desciption;
  int calories;

  public Meal(String type, String desciption, int calories) {
    this.date = new Timestamp(System.currentTimeMillis());
    this.type = type;
    this.desciption = desciption;
    this.calories = calories;
  }

  public Meal() {
  }
}
