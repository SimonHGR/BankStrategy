package bankstrategy;

import java.time.LocalDate;

public interface GrantWithdrawalStrategy {
    boolean grantWithdrawal(int balance, int amount, LocalDate today);
}
