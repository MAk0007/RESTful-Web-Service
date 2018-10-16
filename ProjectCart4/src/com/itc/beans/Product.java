package com.itc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")	
public class Product {
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="pname")
	private String pname;
	
	@Column(name ="price")
	private double price;
	
	@Column(name ="quantity")
	private int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String pname, double price, int quantity) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.quantity = quantity;
	}
	
}
