package bankstrategy;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class OneWithdrawalPerMonthStrategy implements GrantWithdrawalStrategy {
    private LocalDate lastWithdrawal = LocalDate.of(1900, Month.JANUARY, 1);
    
    @Override
    public boolean grantWithdrawal(int balance, int amount, LocalDate today) {
        if (lastWithdrawal.plus(1, ChronoUnit.MONTHS).isBefore(today)) {
            lastWithdrawal = today;
            return true;
        } else return false;
    }
}
