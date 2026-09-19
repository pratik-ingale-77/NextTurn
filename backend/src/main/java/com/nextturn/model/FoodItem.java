package com.nextturn.model;

import jakarta.persistence.*;

@Entity
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private double price;

    private boolean available;

    /*
     * Flavor support
     *
     * Example:
     * Chips -> Cream & Onion
     * Chips -> Tangy Tomato
     *
     * For foods without flavors, flavor can remain null.
     */
    private String flavor;

    public FoodItem() {
        this.available = true;
    }

    public FoodItem(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = true;
        this.flavor = null;
    }

    public FoodItem(String name, String category, double price, String flavor) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = true;
        this.flavor = flavor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
}
