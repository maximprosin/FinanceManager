package org.example.models;

import lombok.Getter;
import org.example.services.FinancialGoalService;

public class FinancialGoal implements FinancialGoalService {
    @Getter
    private String goalName;
    private double targetAmount;
    private double currentAmount;

    public FinancialGoal(String goalName, double targetAmount, double currentAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
    }

    @Override
    public void updateProcess(double progress) {
        currentAmount += progress;
    }

    @Override
    public String getDetails() {
        return "Название цели: " + goalName + "\nТребуемая сумма: " + targetAmount + "\nНакопленная сумма: " + currentAmount;
    }
}
