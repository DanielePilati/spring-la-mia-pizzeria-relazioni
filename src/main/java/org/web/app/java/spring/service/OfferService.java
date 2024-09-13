package org.web.app.java.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.model.Offer;
import org.web.app.java.spring.repo.OfferRepository;

@Service
public class OfferService {

	@Autowired
	private OfferRepository repo;
	
	public List<Offer> findAllOffer(){
		return repo.findAll(Sort.by("id"));
	}
	
	public Offer findById(Integer id) {
		return repo.findById(id).get();
	}
	
	
	public void create(Offer offer) {
		repo.save(offer);
	}
	public void update(Offer offer) {

		repo.save(offer);
	}
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Offer> findByTitle(String name){
		return repo.findByTitleContains(name);
	}

}
