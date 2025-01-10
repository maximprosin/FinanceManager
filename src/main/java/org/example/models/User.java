package org.example.models;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<String> categories;
    private List<FinancialGoal> financialGoals;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
