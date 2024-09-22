package org.web.app.java.spring.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.service.FoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/foods")
public class FoodRestController {

	@Autowired
	FoodService foodService;

	@GetMapping()
	public List<Food> index(@RequestParam(name = "word", required = false) String word) {

		if (word != null && !word.isEmpty()) {
			return  foodService.findByName(word);
		}
		
		return  foodService.findAllFoods();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Food> show(@PathVariable(name = "id") Integer id){
		
		Optional<Food> food = foodService.findById(id);
		
		if(food.isPresent()) {
			return new ResponseEntity<Food>(food.get(), HttpStatus.OK); 		
		}
		return new ResponseEntity<Food>(HttpStatus.NOT_FOUND); 	

	}
	
	@PostMapping()
	public Food store(@Valid @RequestBody Food Food) {
		
		Food newfood = foodService.create(Food);
		
		return newfood;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> update(@RequestBody Food food, @PathVariable(name = "id") Integer id) {
		
		Optional<Food> oldfood = foodService.findById(id);
		
		if(oldfood.isPresent()) {
			Food newFood = foodService.update(food);
			return new ResponseEntity<Food>(newFood, HttpStatus.OK); 		
		}
		return new ResponseEntity<Food>(HttpStatus.NOT_FOUND); 	

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Food> delete(@PathVariable(name = "id") Integer id) {
		
		Optional<Food> food = foodService.findById(id);
		
		if(food.isPresent()) {
			foodService.deleteById(id);
			return new ResponseEntity<Food>(food.get(), HttpStatus.OK); 		
		}
		return new ResponseEntity<Food>(HttpStatus.NOT_FOUND); 		
		
	}
	

}
