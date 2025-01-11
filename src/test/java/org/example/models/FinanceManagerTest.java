package org.example.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FinanceManagerTest {
    private FinanceManager financeManager;
    private static final String TEST_FILE_NAME = "src/main/java/org/example/data/usersData.txt";

    @BeforeEach
    public void setUp() {
        financeManager = new FinanceManager();
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testRegisterUser() {
        User user = new User("testUser", "password123");
        financeManager.registerUser(user);
        assertEquals(user.getUsername(), financeManager.login("testUser", "password123").getUsername());
    }

    @Test
    public void testRegisterDuplicateUser() {
        User user1 = new User("testUser", "password123");
        financeManager.registerUser(user1);

        User user2 = new User("testUser", "password456");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            financeManager.registerUser(user2);
        });

        assertEquals("Пользователь с таким именем уже существует.", exception.getMessage());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("testUser", "password123");
        financeManager.registerUser(user);

        User loggedInUser = financeManager.login("testUser", "password123");
        assertNotNull(loggedInUser);
        assertEquals("testUser", loggedInUser.getUsername());
    }

    @Test
    public void testLoginFailure() {
        User user = new User("testUser", "password123");
        financeManager.registerUser(user);

        User loggedInUser = financeManager.login("testUser", "wrongPassword");
        assertNull(loggedInUser);

        loggedInUser = financeManager.login("wrongUser", "password123");
        assertNull(loggedInUser);
    }

    @Test
    public void testLoadData() {
        User user = new User("testUser", "password123");
        financeManager.registerUser(user);

        FinanceManager newFinanceManager = new FinanceManager();

        User loadedUser = newFinanceManager.login("testUser", "password123");
        assertNotNull(loadedUser);
        assertEquals("testUser", loadedUser.getUsername());
    }
}