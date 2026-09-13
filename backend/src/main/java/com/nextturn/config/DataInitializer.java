package com.nextturn.config;

import com.nextturn.model.FoodItem;
import com.nextturn.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadFoodItems(FoodItemRepository repository) {

        return args -> {

            if (repository.count() > 0) {
                return;
            }

            repository.save(new FoodItem("Chips", "Snacks", 10));
            repository.save(new FoodItem("Kurkure", "Snacks", 10));
            repository.save(new FoodItem("Samosa", "Snacks", 15));
            repository.save(new FoodItem("Vada Pav", "Snacks", 20));

            repository.save(new FoodItem("Veg Sandwich", "Fast Food", 40));
            repository.save(new FoodItem("Cheese Sandwich", "Fast Food", 50));
            repository.save(new FoodItem("Veg Burger", "Fast Food", 60));
            repository.save(new FoodItem("French Fries", "Fast Food", 50));
            repository.save(new FoodItem("Veg Roll", "Fast Food", 45));

            repository.save(new FoodItem("Veg Biryani", "Meals", 80));
            repository.save(new FoodItem("Veg Thali", "Meals", 90));
            repository.save(new FoodItem("Misal Pav", "Meals", 45));

            repository.save(new FoodItem("Frooti", "Drinks", 20));
            repository.save(new FoodItem("Masala Chai", "Drinks", 15));
            repository.save(new FoodItem("Cold Coffee", "Drinks", 40));
            repository.save(new FoodItem("Lemon Juice", "Drinks", 25));
            repository.save(new FoodItem("Soft Drink", "Drinks", 30));
        };
    }
          }
