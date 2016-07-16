package bankstrategy;

import java.time.LocalDate;

public class OverdraftFeeStrategy implements ApplyFeesStrategy{
    private static final int FEE = -35;
    
    @Override
    public int calculateFee(int balance, int amount, LocalDate today) {
        return (balance + amount < 0) ? FEE : 0;
    }
}
