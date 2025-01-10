package org.example.models;

import java.util.Date;

public class Transaction {
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
}