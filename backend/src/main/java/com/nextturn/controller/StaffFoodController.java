package com.nextturn.controller;

import com.nextturn.model.FoodItem;
import com.nextturn.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff/food")
@CrossOrigin
public class StaffFoodController {

    private final FoodItemService foodItemService;

    public StaffFoodController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public List<FoodItem> getFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<FoodItem> markAvailable(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    foodItemService.updateAvailability(id, true)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/unavailable")
    public ResponseEntity<FoodItem> markUnavailable(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    foodItemService.updateAvailability(id, false)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
