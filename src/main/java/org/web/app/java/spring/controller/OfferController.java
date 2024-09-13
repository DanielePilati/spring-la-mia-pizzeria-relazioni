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
import org.web.app.java.spring.model.Offer;
import org.web.app.java.spring.service.OfferService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	
	@GetMapping()
	public String index(Model model) {
		
		model.addAttribute("offers", offerService.findAllOffer());
		model.addAttribute("search", new Offer());
		
		return "/offers/index";
	}
	
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer offerId) {

		model.addAttribute("offers", offerService.findById(offerId));

		return "/offers/show";
	}
	
	@GetMapping("/search/")
	public String search(Model model, @RequestParam("title") String title) {

		model.addAttribute("search", new Offer());
		model.addAttribute("offers", offerService.findByTitle(title));

		return "/offers/index";
	}
	
	//CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("offer", new Offer());

		return "/offers/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/offers/create";
		}

		offerService.create(formOffer);
		
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/foods/show/" + formOffer.getFood().getId();
	}
	
	// UPDATE
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer offerId) {

		model.addAttribute("offer", offerService.findById(offerId));

		return "/offers/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/offers/edit";
		}

		offerService.update(formOffer);

		attributes.addFlashAttribute("offer", offerService.findById(formOffer.getId()));
		attributes.addFlashAttribute("message", "Updated");
		attributes.addFlashAttribute("class", "warning");

		return "redirect:/offers";
	}
	
	// DELETE
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer offerId, RedirectAttributes attributes) {

		offerService.deleteById(offerId);

		attributes.addFlashAttribute("message", "Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/offers";
	}
	
}
