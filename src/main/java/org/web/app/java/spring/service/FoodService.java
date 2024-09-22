package org.web.app.java.spring.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.repo.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository repo;
	
	public List<Food> findAllFoods(){
		return repo.findAll(Sort.by("id"));
	}
	
	public Optional<Food> findById(Integer id) {
		return repo.findById(id);
	}
	
	public List<Food> findByName(String name){
		return repo.findByNameContains(name);
	}
	
	public Food create(Food food) {
		return repo.save(food);
	}
	public Food update(Food food) {
		food.setUpdatedAt(Instant.now());
		return repo.save(food);
	}
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
