package com.greenfox.caloriecounter.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;

  @NotNull
  String date;

  @NotNull
  String type;

  @NotNull
  String description;

  @NotNull
  int calories;

  public Meal(String type, String description, int calories) {
    Date newDate = new Date();
    this.date = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").format(newDate);
    this.type = type;
    this.description = description;
    this.calories = calories;
  }

  public Meal() {
  }

  public Meal(String date, String type, String description, int calories) {
    this.date = date;
    this.type = type;
    this.description = description;
    this.calories = calories;
  }


  public Meal(long id, String date, String type, String description, int calories) {
    this.id = id;
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
