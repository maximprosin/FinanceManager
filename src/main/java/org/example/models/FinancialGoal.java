package org.example.models;

import org.example.services.FinancialGoalService;

public class FinancialGoal implements FinancialGoalService {
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
}
