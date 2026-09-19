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

    /*
     * Get all food items.
     *
     * This includes unavailable items because Staff needs
     * to see which foods/flavors are currently unavailable.
     */
    @GetMapping
    public List<FoodItem> getFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    /*
     * Get all flavors for a particular food.
     *
     * Example:
     * /api/staff/food/flavors/Chips
     */
    @GetMapping("/flavors/{foodName}")
    public ResponseEntity<List<FoodItem>> getFlavors(
            @PathVariable String foodName) {

        return ResponseEntity.ok(
                foodItemService.getFlavors(foodName)
        );
    }

    /*
     * Make a complete food item/flavor available.
     */
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

    /*
     * Make a complete food item/flavor unavailable.
     */
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

    /*
     * Make a SPECIFIC FLAVOR available.
     *
     * Example:
     * Chips -> Cream & Onion
     */
    @PutMapping("/{id}/flavor/available")
    public ResponseEntity<FoodItem> makeFlavorAvailable(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    foodItemService.updateFlavorAvailability(id, true)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Make a SPECIFIC FLAVOR unavailable.
     *
     * Example:
     * Chips -> Tangy Tomato -> Unavailable
     *
     * Other Chips flavors remain available.
     */
    @PutMapping("/{id}/flavor/unavailable")
    public ResponseEntity<FoodItem> makeFlavorUnavailable(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    foodItemService.updateFlavorAvailability(id, false)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Get one exact food + flavor combination.
     *
     * Example:
     * /api/staff/food/flavor/Chips/Cream%20%26%20Onion
     */
    @GetMapping("/flavor/{foodName}/{flavor}")
    public ResponseEntity<FoodItem> getSpecificFlavor(
            @PathVariable String foodName,
            @PathVariable String flavor) {

        return foodItemService
                .getFoodItemByNameAndFlavor(foodName, flavor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
