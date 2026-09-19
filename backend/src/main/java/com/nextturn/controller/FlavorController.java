package com.nextturn.controller;

import com.nextturn.model.FoodItem;
import com.nextturn.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin
public class FlavorController {

    private final FoodItemService foodItemService;

    public FlavorController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    /*
     * Get all flavors for a food.
     *
     * Example:
     * GET /api/food/flavors/Chips
     */
    @GetMapping("/flavors/{foodName}")
    public ResponseEntity<List<FoodItem>> getFlavors(
            @PathVariable String foodName) {

        return ResponseEntity.ok(
                foodItemService.getFlavors(foodName)
        );
    }

    /*
     * Get one exact food + flavor.
     *
     * Example:
     * GET /api/food/flavor/Chips/Cream%20%26%20Onion
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
