package bankstrategy;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FrequentWithdrawalFeeStrategy implements ApplyFeesStrategy {
    private static final int FEE = -35;
    private List<LocalDate> transactionHistory = new LinkedList<>();
    
    @Override
    public int calculateFee(int balance, int amount, LocalDate today) {
        // clean out old transactions from history
        Iterator<LocalDate> ild = transactionHistory.iterator();
        while (ild.hasNext()) {
           LocalDate testThisDate = ild.next();
           if (Duration.between(testThisDate.atStartOfDay(), today.atStartOfDay())
                   .toDays() > 30) {
               ild.remove();
           }
        }

        transactionHistory.add(today);
        // then report fee based on count of recent transactions
        return (transactionHistory.size() > 5) ? FEE : 0; 
    }
}
