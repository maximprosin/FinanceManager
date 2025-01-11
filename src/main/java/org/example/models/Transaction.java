package org.example.models;

import lombok.Getter;
import org.example.services.TransactionService;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements TransactionService {
    @Getter
    private int id;
    @Getter
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Transaction(int id, double amount, String category, String description) {
        this.id = id;
        this.date = LocalDate.now();
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    @Override
    public String getDetails() {
        return "ID: " + id + "\nДата: " + date + "\nСумма: " + amount + "\nКатегория: " + category + "\nОписание: " + description;
    }
}