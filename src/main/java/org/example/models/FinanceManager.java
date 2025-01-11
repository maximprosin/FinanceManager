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
        loadData(); // Загружаем данные при инициализации
    }

    @Override
    public void registerUser(User user) {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("Пользователь с таким именем уже существует.");
            }
        }
        users.add(user);
        saveData(); // Сохраняем данные в файл после добавления нового пользователя
    }

    @Override
    public User login(String username, String password) {
        // Метод loadData уже вызывается в конструкторе, поэтому здесь мы просто проверяем список
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Возвращаем пользователя при успешном входе
            }
        }
        return null; // Если пользователь не найден или пароль неверный
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
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.add(new User(username, password)); // Добавляем пользователя в список
                }
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка при загрузке данных: ", e);
        }
    }
}