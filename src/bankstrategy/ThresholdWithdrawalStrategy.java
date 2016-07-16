package bankstrategy;

import java.time.LocalDate;

public class ThresholdWithdrawalStrategy implements GrantWithdrawalStrategy {
    private int minBalance;

    public ThresholdWithdrawalStrategy(int minBalance) {
        this.minBalance = minBalance;
    }
    
    @Override
    // amount is negative for withdrawal 
    public boolean grantWithdrawal(int balance, int amount, LocalDate today) {
        return balance + amount > minBalance;
    }
}
