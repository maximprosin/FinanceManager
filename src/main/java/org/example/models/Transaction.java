package org.example.models;

import org.example.services.TransactionService;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements TransactionService {
    private Date date;
    private double amount;
    private String category;
    private String description;

    public Transaction(Date date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getDetails() {
        return "Date: " + date + "\nAmount: " + amount + "\nCategory: " + category + "\nDescription: " + description;
    }
}