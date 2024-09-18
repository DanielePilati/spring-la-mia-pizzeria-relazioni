package org.web.app.java.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	 public List<Ingredient> findByNameContains(String name);

	
}
