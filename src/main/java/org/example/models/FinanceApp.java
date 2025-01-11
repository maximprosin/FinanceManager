package org.example.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FinanceApp {
    private final FinanceManager financeManager;
    private User user;
    private final Scanner scanner;
    private int idCounter;

    public FinanceApp() {
        this.financeManager = new FinanceManager();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("1. Войти");
        System.out.println("2. Зарегистрироваться");
        System.out.print("Выберите действие: ");
    }

    public void displayActionsMenu() {
        System.out.println("1. Добавление транзакции");
        System.out.println("2. Изменение транзакции");
        System.out.println("3. Удаление транзакции");
        System.out.println("4. Добавление категории");
        System.out.println("5. Мои категории");
        System.out.println("6. Генерация отчета");
        System.out.println("7. Установка финансовой цели");
        System.out.println("8. Положить деньги на цель");
        System.out.println("9. Мои финансовые цели");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    public void addTransaction() {
        System.out.println("Введите сумму транзакции:");
        double transactionAmount = Double.parseDouble(scanner.nextLine());
        System.out.println("Выберите категорию:");
        getCategories();
        String transactionCategory = scanner.nextLine();
        System.out.println("Введите описание:");
        String transactionDescription = scanner.nextLine();
        Transaction newTransaction = new Transaction(++idCounter, transactionAmount, transactionCategory, transactionDescription);
        user.addTransaction(newTransaction);
        System.out.println("Транзакция добавлена успешно!");
    }

    public void editTransaction() {
        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction.getDetails());
        }

        System.out.println("Введите ID транзакции для ее изменения:");
        int id = Integer.parseInt(scanner.nextLine());

        Transaction existingTransaction = null;
        for (Transaction transaction : user.getTransactions()) {
            if (transaction.getId() == id) {
                existingTransaction = transaction;
                break;
            }
        }

        if (existingTransaction == null) {
            System.out.println("Транзакция с таким ID не найдена.");
            return;
        }

        System.out.println("Введите обновленную сумму транзакции:");
        double newTransactionAmount = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите категорию:");
        String newTransactionCategory = scanner.nextLine();
        System.out.println("Введите описание:");
        String newTransactionDescription = scanner.nextLine();

        Transaction updatedTransaction = new Transaction(existingTransaction.getId(), newTransactionAmount, newTransactionCategory, newTransactionDescription);

        user.editTransaction(id, updatedTransaction);

        System.out.println("Транзакция изменена успешно!");
    }


    public void removeTransaction() {
        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction.getDetails());
        }
        System.out.println("Введите ID транзакции для ее удаления:");
        int id = Integer.parseInt(scanner.nextLine());
        user.removeTransaction(id);
        System.out.println("Транзакция удалена успешно!");
    }

    public void addCategory() {
        System.out.println("Введите название категории:");
        String newCategory = scanner.nextLine();
        user.addCategory(newCategory);
    }

    public void getCategories() {
        System.out.println("Ваши категории:");
        for (String category : user.getCategories()) {
            System.out.println(category);
        }
    }

    public void getReport() {
        System.out.println("Введите начальную дату (дд.мм.гггг):");
        LocalDate startDate = DateParser.parse(scanner.nextLine());
        System.out.println("Введите конечную дату (дд.мм.гггг):");
        LocalDate endDate = DateParser.parse(scanner.nextLine());
        for (Transaction transaction : user.getReport(startDate, endDate)) {
            System.out.println(transaction.getDetails());
        }
    }

    public void addFinancialGoal() {
        System.out.println("Введите название финансовой цели:");
        String goalName = scanner.nextLine();
        System.out.println("Введите сумму, которую хотите накопить:");
        double goalTargetAmount = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите сумму, с которой хотите начать:");
        double goalCurrentAmount = Double.parseDouble(scanner.nextLine());

        FinancialGoal goal = new FinancialGoal(goalName, goalTargetAmount, goalCurrentAmount);
        user.addFinancialGoal(goal);

        System.out.println("Цель была успешно добавлена!");
    }

    public void updateProcess() {
        for (FinancialGoal financialGoal : user.getFinancialGoals()) {
            System.out.println(financialGoal.getDetails());
        }
        System.out.print("Введите название финансовой цели: ");
        String goalName = scanner.nextLine();

        FinancialGoal goalToUpdate = null;

        for (FinancialGoal goal : user.getFinancialGoals()) {
            if (goal.getGoalName().equalsIgnoreCase(goalName)) {
                goalToUpdate = goal;
                break;
            }
        }

        if (goalToUpdate != null) {
            System.out.print("Введите прогресс для обновления: ");
            double progress = Double.parseDouble(scanner.nextLine());
            goalToUpdate.updateProcess(progress);
            System.out.println("Цель обновлена: " + goalToUpdate.getDetails());
        } else {
            System.out.println("Финансовая цель с таким названием не найдена.");
        }
    }

    public void getFinancialGoals() {
        System.out.println("Ваши финансовые цели:");
        for (FinancialGoal financialGoal : user.getFinancialGoals()) {
            System.out.println(financialGoal.getDetails());
        }
    }

    public static class DateParser {
        static DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd.MM.yyyy");

        static LocalDate parse(String date) {
                try {
                    return LocalDate.parse(date, format);
                } catch (DateTimeParseException ignored) {
                }
            return null;
        }
    }


    public void start() {
        displayMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                user = financeManager.login(login, password);
                break;
            case 2:
                System.out.println("Придумайте логин:");
                String newLogin = scanner.nextLine();
                System.out.println("Придумайте пароль:");
                String newPassword = scanner.nextLine();
                user = new User(newLogin, newPassword);
                financeManager.registerUser(user);
                break;
        }


        while (true) {
            displayActionsMenu();
            int choiceAction = Integer.parseInt(scanner.nextLine());
            switch (choiceAction) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    editTransaction();
                    break;
                case 3:
                    removeTransaction();
                    break;
                case 4:
                    addCategory();
                    break;
                case 5:
                    getCategories();
                    break;
                case 6:
                    getReport();
                    break;
                case 7:
                    addFinancialGoal();
                    break;
                case 8:
                    updateProcess();
                    break;
                case 9:
                    getFinancialGoals();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
            System.out.println();
        }
    }
}
