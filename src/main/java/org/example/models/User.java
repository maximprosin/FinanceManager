package org.example.models;

import lombok.Getter;
import org.example.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class User implements UserService {
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<String> categories;
    private List<FinancialGoal> financialGoals;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.financialGoals = new ArrayList<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(int id) {
        transactions.removeIf(transaction -> transaction.getId() == id);
    }

    @Override
    public void editTransaction(int id, Transaction updatedTransaction) {
        int index = -1;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        transactions.set(index, updatedTransaction);
    }

    @Override
    public List<Transaction> getReport(LocalDate startDate, LocalDate endDate) {
        List<Transaction> report = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate(); // Предполагается, что getDate() возвращает LocalDate
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
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

    @Override
    public void addFinancialGoal(FinancialGoal financialGoal) {
        financialGoals.add(financialGoal);
    }
}
