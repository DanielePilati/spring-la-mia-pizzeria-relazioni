package org.web.app.java.spring.model;

import java.text.NumberFormat;
import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Integer id;

	@NotEmpty
	@Column(name = "food_name", nullable = false)
	private String name;

	@NotEmpty
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "img_url", columnDefinition = "LONGTEXT")
	private String imgUrl;

	@NotNull
	@PositiveOrZero
	@Column(name = "food_price", nullable = false)
	private Double price;

	@UpdateTimestamp
	private Instant updatedAt;

	@OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Offer> offers;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable( 
			name = "food_ingredient", 
			joinColumns = @JoinColumn(name = "food_id"), 
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
			)
	@JsonManagedReference
	private List<Ingredient> ingredients;
	
	public void addIngredient(Ingredient ingredient) {
	    this.ingredients.add(ingredient);
	    ingredient.getFoods().add(this);
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
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

	public Double getPrice() {

		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPriceFormatted() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(price);
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", description=" + description + ", imgUrl=" + imgUrl + ", price="
				+ price + ", updatedAt=" + updatedAt + ", offers=" + offers + ", ingredients=" + ingredients + "]";
	}
	
	
	

}
