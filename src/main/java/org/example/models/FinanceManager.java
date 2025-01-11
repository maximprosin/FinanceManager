package org.example.models;

import org.example.services.FinanceManagerService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinanceManager implements FinanceManagerService {
    private List<User> users;
    private static final String FILE_NAME = "src/main/java/org/example/data/usersData.txt";
    private static final Logger logger = LoggerFactory.getLogger(FinanceManager.class);

    public FinanceManager() {
        this.users = new ArrayList<>();
        loadData();
    }

    @Override
    public void registerUser(User user) {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("Пользователь с таким именем уже существует.");
            }
        }
        users.add(user);
        saveData();
    }

    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка при сохранении данных: ", e);
        }
    }

    @Override
    public void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                logger.info("Файл {} был создан.", FILE_NAME);
            } catch (IOException e) {
                logger.error("Не удалось создать файл: ", e);
                return;
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.add(new User(username, password));
                }
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка при загрузке данных: ", e);
        }
    }
}