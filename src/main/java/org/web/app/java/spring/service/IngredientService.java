package org.web.app.java.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.model.Ingredient;
import org.web.app.java.spring.repo.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepo;
	
	public List<Ingredient> findAllIngredients(){
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(Integer id) {
		return ingredientRepo.findById(id).get();
	}
	
	public List<Ingredient> findByName(String name){
		return ingredientRepo.findByNameContains(name);
	}
	
	public void create(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void update(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void deleteById(Integer id) {
		ingredientRepo.deleteById(id);
	}
	
	
}
