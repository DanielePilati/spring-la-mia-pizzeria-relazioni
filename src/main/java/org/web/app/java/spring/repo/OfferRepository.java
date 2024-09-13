package org.web.app.java.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer>  {

	 public List<Offer> findByTitleContains(String title);
	
	
}
