package org.example.models;

public class FinancialGoal {
    private String goalName;
    private double targetAmount;
    private double currentAmount;

    public FinancialGoal(String goalName, double targetAmount, double currentAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
    }
}
