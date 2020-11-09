package com.example.model;

import javax.persistence.*;


/**
 * Products.java
 * This is a model class represents a Products entity
*/

@Entity
@Table(name="Products" , schema = "demo")
public class Products {
	
	@Id
	@Column(name="barcode")
	protected String barcode;
	
	@Column(name="name")
	protected String name;
	
	@Column(name="color")
	protected String color;
	 
	@Column(name="description")
	protected String description;
	
	public Products() {
	}
	
	public Products(String barcode, String name, String color, String description) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.color = color;
		this.description = description;
	}

	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
