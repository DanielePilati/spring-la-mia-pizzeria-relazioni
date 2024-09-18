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
import org.web.app.java.spring.model.Offer;
import org.web.app.java.spring.service.FoodService;
import org.web.app.java.spring.service.IngredientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private IngredientService ingredientService;

	// READ
	@GetMapping()
	public String index(Model model) {

		model.addAttribute("foods", foodService.findAllFoods());
		model.addAttribute("search", new Food());

		return "/foods/index";
	}
	
	// SHOW
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer foodId) {

		model.addAttribute("foods", foodService.findById(foodId));

		return "/foods/show";
	}
	
	@GetMapping("/show/{id}/offer")
	public String offer(Model model, @PathVariable("id") Integer foodId) {
		Offer offer = new Offer();
		offer.setFood(foodService.findById(foodId));
		model.addAttribute("offer", offer);

		return "/offers/create";
	}
	
	//SEARCH
	@GetMapping("/search/")
	public String search(Model model, @RequestParam("name") String name) {

		model.addAttribute("search", new Food());
		model.addAttribute("foods", foodService.findByName(name));

		return "/foods/index";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("food", new Food());
		model.addAttribute("ingredients", ingredientService.findAllIngredients());

		return "/foods/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("food") Food formFood, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("ingredients", ingredientService.findAllIngredients());
			return "/foods/create";
		}

		foodService.create(formFood);
		
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/foods";
	}

	// UPDATE
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer foodId) {

		model.addAttribute("food", foodService.findById(foodId));
		model.addAttribute("ingredients", ingredientService.findAllIngredients());

		return "/foods/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("food") Food formFood, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("ingredients", ingredientService.findAllIngredients());
			return "/foods/edit";
		}

		foodService.update(formFood);

		attributes.addFlashAttribute("food", foodService.findById(formFood.getId()));
		attributes.addFlashAttribute("message", "Updated");
		attributes.addFlashAttribute("class", "warning");

		return "redirect:/foods";
	}

	// DELETE
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer foodId, RedirectAttributes attributes) {

		foodService.deleteById(foodId);

		attributes.addFlashAttribute("message", "Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/foods";
	}

}
