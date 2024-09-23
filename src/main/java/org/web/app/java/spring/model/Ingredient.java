package org.web.app.java.spring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "Ingredients")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_id")
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable( 
			name = "food_ingredient", 
			joinColumns = @JoinColumn(name = "ingredient_id"), 
			inverseJoinColumns = @JoinColumn(name = "food_id")
			)
	@JsonManagedReference
	private List<Food> foods;
	

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
