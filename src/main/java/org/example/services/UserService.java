package org.example.services;

import org.example.models.Transaction;

import java.util.Date;
import java.util.List;

public interface UserService {
    void addTransaction(Transaction transaction);
    void removeTransaction(Transaction transaction);
    void editTransaction(Transaction transaction);
    List<Transaction> getReport(Date startDate, Date endDate);
    void addCategory(String category);
    void removeCategory(String category);
}
