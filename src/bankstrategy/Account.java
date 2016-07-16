package bankstrategy;

import java.time.LocalDate;

public class Account {
    private String holder;
    private int accountId;
    private int balance;
    private ApplyFeesStrategy applyFeesStrategy;
    private GrantWithdrawalStrategy grantWithdrawalStrategy;
    
    public Account(ApplyFeesStrategy applyFeesStrategy, GrantWithdrawalStrategy grantWithdrawalStrategy) {
        if (applyFeesStrategy == null || grantWithdrawalStrategy == null) {
            throw new IllegalArgumentException("Strategies must not be null");
        }
        this.applyFeesStrategy = applyFeesStrategy;
        this.grantWithdrawalStrategy = grantWithdrawalStrategy;
    }
    
    public void setApplyFeesStrategy(ApplyFeesStrategy applyFeesStrategy) {
        if (applyFeesStrategy == null) {
            throw new IllegalArgumentException("Strategies must not be null");
        }
        this.applyFeesStrategy = applyFeesStrategy;
    }
    
    public void setGrantWithdrawalStrategy(GrantWithdrawalStrategy grantWithdrawalStrategy) {
        if (grantWithdrawalStrategy == null) {
            throw new IllegalArgumentException("Strategies must not be null");
        }
        this.grantWithdrawalStrategy = grantWithdrawalStrategy;
    }

    public int getBalance() {
        return this.balance;
    }
    
    private void transaction(String description, int amount, LocalDate today) {
        int result = balance + amount;
        System.out.println(today + ": " + accountId + ", " + description 
                + ". Amount " + amount + " final balance: " + result);
        balance = result;
    }
    
    public int withdrawal(int amount, LocalDate date) {
        if (grantWithdrawalStrategy.grantWithdrawal(balance, -amount, date)) {
            int fee = this.applyFeesStrategy.calculateFee(balance, -amount, date);
            transaction("Withdrawal, basic amount " + amount, fee - amount, date);
            return amount;
        } else {
            transaction("Withdrawal declined", 0, date);
            return 0;
        }
    }
    
    public int deposit(int amount, LocalDate date) {
        transaction("Deposit", amount, date);
        return amount;
    }
}
