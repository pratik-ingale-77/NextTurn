package com.nextturn.service;

import com.nextturn.model.FoodItem;
import com.nextturn.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    // Get every food item, including unavailable items
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    // Get only available food items
    public List<FoodItem> getAvailableFoodItems() {
        return foodItemRepository.findByAvailable(true);
    }

    // Get food items by category
    public List<FoodItem> getFoodItemsByCategory(String category) {
        return foodItemRepository.findByCategory(category);
    }

    // Get food items by name
    public List<FoodItem> getFoodItemsByName(String name) {
        return foodItemRepository.findByName(name);
    }

    // Get all flavors of a particular food
    public List<FoodItem> getFlavors(String foodName) {
        return foodItemRepository.findByName(foodName);
    }

    // Get a specific flavor
    public Optional<FoodItem> getFoodItemByNameAndFlavor(
            String foodName,
            String flavor
    ) {
        return foodItemRepository.findByNameAndFlavor(foodName, flavor);
    }

    // Add a new food item or flavor
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    // Find food item by ID
    public Optional<FoodItem> getFoodItemById(Long id) {
        return foodItemRepository.findById(id);
    }

    // Change availability of a complete food item
    public FoodItem updateAvailability(Long id, boolean available) {

        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Food item not found")
                );

        foodItem.setAvailable(available);

        return foodItemRepository.save(foodItem);
    }

    // Make a specific flavor available/unavailable
    public FoodItem updateFlavorAvailability(
            Long id,
            boolean available
    ) {

        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Food item/flavor not found")
                );

        foodItem.setAvailable(available);

        return foodItemRepository.save(foodItem);
    }
}
