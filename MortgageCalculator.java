package com.company;

public class MortgageCalculator {
    private int principal;
    private float annualInterest;
    private byte years;

    final public static byte MONTHS_IN_YEAR = 12;
    final public static byte PERCENT = 100;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateMortgage(
            ) {

        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }


    public double calculateBalance(
            short numberOfPaymentsMade
    ) {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        return principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }
    public double[] getRemainingBalance(){
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= getNumberOfPayments(); month++) {
            double balance = calculateBalance(month);
            balances[month-1] = balance;
    }   return balances;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
