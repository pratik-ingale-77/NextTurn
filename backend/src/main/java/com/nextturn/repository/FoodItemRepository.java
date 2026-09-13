package com.nextturn.repository;

import com.nextturn.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByCategory(String category);

    List<FoodItem> findByAvailable(boolean available);
}
