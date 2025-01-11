package org.example.services;

import org.example.models.FinancialGoal;
import org.example.models.Transaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {
    void addTransaction(Transaction transaction);
    void removeTransaction(int id);
    void editTransaction(int id, Transaction transaction);
    List<Transaction> getReport(LocalDate startDate, LocalDate endDate);
    void addCategory(String category);
    void removeCategory(String category);
    void addFinancialGoal(FinancialGoal financialGoal);
}
