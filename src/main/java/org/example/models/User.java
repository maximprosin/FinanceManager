package org.example.models;

import org.example.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements UserService {
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<String> categories;
    private List<FinancialGoal> financialGoals;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void editTransaction(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        transactions.set(index, transaction);
    }

    @Override
    public List<Transaction> getReport(Date startDate, Date endDate) {
        List<Transaction> report = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.getDate().before(startDate) && !transaction.getDate().after(endDate)) {
                report.add(transaction);
            }
        }
        return report;
    }

    @Override
    public void addCategory(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    @Override
    public void removeCategory(String category) {
        categories.remove(category);
    }
}
