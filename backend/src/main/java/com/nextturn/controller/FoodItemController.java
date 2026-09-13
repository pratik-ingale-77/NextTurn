package com.nextturn.controller;

import com.nextturn.model.FoodItem;
import com.nextturn.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/available")
    public List<FoodItem> getAvailableFoodItems() {
        return foodItemService.getAvailableFoodItems();
    }

    @GetMapping("/category/{category}")
    public List<FoodItem> getByCategory(@PathVariable String category) {
        return foodItemService.getFoodItemsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<FoodItem> addFoodItem(
            @RequestBody FoodItem foodItem) {

        return ResponseEntity.ok(
                foodItemService.addFoodItem(foodItem)
        );
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<FoodItem> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        try {
            return ResponseEntity.ok(
                    foodItemService.updateAvailability(id, available)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
                                                      }
