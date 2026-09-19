package com.nextturn.repository;

import com.nextturn.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByCategory(String category);

    List<FoodItem> findByAvailable(boolean available);

    // Find all items/flavors belonging to a food name
    List<FoodItem> findByName(String name);

    // Find one exact food + flavor combination
    Optional<FoodItem> findByNameAndFlavor(String name, String flavor);
}
