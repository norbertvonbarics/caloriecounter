package com.greenfox.caloriecounter.repository;

import com.greenfox.caloriecounter.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {

}
