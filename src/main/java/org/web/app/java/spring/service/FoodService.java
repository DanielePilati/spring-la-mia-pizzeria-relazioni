package org.web.app.java.spring.service;

import java.time.Instant;
import java.util.List;

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
		return repo.findAll(Sort.by("updatedAt"));
	}
	
	public Food findById(Integer id) {
		return repo.findById(id).get();
	}
	
	public List<Food> findByName(String name){
		return repo.findByNameContains(name);
	}
	
	public void create(Food food) {
		repo.save(food);
	}
	public void update(Food food) {
		food.setUpdatedAt(Instant.now());
		repo.save(food);
	}
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
