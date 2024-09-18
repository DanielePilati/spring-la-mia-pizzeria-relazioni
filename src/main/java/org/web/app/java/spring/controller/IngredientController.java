package org.web.app.java.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.model.Food;
import org.web.app.java.spring.model.Ingredient;
import org.web.app.java.spring.service.FoodService;
import org.web.app.java.spring.service.IngredientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService; 
	@Autowired
	private FoodService foodService; 
	
	//READ
	@GetMapping()
	public String index(Model model) {
		
		model.addAttribute("ingredients", ingredientService.findAllIngredients());
		model.addAttribute("search", new Ingredient());
		
		return"/ingredients/index";
	}
	
	//SEARCH
	@GetMapping("/search/")
	public String search(Model model, @RequestParam("name") String name) {

		model.addAttribute("search", new Food());
		model.addAttribute("ingredients", ingredientService.findByName(name));

		return "/ingredients/index";
	}
	
	// SHOW
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer ingredientId) {

		model.addAttribute("ingredients", ingredientService.findById(ingredientId));

		return "/ingredients/show";
	}
	
	// CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("ingredient", new Ingredient());
		model.addAttribute("foods", foodService.findAllFoods());
		
		return "/ingredients/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("foods", foodService.findAllFoods());
			return "/ingredients/create";
		}

		ingredientService.create(formIngredient);
		
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/ingredients";
	}
	
	// UPDATE
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer ingredientId) {

		model.addAttribute("ingredient", ingredientService.findById(ingredientId));
		model.addAttribute("foods", foodService.findAllFoods());

		return "/ingredients/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("food") Ingredient formIngredient, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("foods", foodService.findAllFoods());
			return "/ingredients/edit";
		}

		ingredientService.update(formIngredient);

		attributes.addFlashAttribute("ingredient", ingredientService.findById(formIngredient.getId()));
		attributes.addFlashAttribute("message", "Updated");
		attributes.addFlashAttribute("class", "warning");

		return "redirect:/ingredients";
	}

	// DELETE
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer ingredientId, RedirectAttributes attributes) {

		ingredientService.deleteById(ingredientId);

		attributes.addFlashAttribute("message", "Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/ingredients";
	}

}
