package com.nextturn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DatabaseTestController {

    private final DataSource dataSource;

    public DatabaseTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/api/database-test")
    public String databaseTest() {

        try (Connection connection = dataSource.getConnection()) {

            if (connection.isValid(5)) {
                return "NextTurn MySQL Database Connected Successfully!";
            }

            return "Database connection failed.";

        } catch (Exception e) {
            return "Database connection error: " + e.getMessage();
        }
    }
}
