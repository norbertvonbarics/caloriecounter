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
  String description;
  int calories;

  public Meal(String type, String description, int calories) {
    this.date = new Timestamp(System.currentTimeMillis());
    this.type = type;
    this.description = description;
    this.calories = calories;
  }

  public Meal() {
  }

  public Meal(Timestamp date, String type, String description, int calories) {
    this.date = date;
    this.type = type;
    this.description = description;
    this.calories = calories;
  }

  @Override
  public String toString() {
    return "Meal{" +
        "id=" + id +
        ", date=" + date +
        ", type='" + type + '\'' +
        ", description='" + description + '\'' +
        ", calories=" + calories +
        '}';
  }
}
