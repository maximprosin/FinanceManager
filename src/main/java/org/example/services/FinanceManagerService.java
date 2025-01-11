package org.example.services;

import org.example.models.User;

public interface FinanceManagerService {
    void registerUser(User user);
    User login(String username, String password);
    void saveData();
    void loadData();
}
