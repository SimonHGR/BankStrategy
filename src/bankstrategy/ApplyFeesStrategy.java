package bankstrategy;

import java.time.LocalDate;

public interface ApplyFeesStrategy {
    int calculateFee(int balance, int amount, LocalDate today);
}
