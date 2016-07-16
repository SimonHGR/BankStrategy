package bankstrategy;

import java.time.LocalDate;
import java.time.Month;

public class BankStrategy {

    public static void main(String[] args) {
        Account a = new Account(new OverdraftFeeStrategy(), new ThresholdWithdrawalStrategy(-1000));
        // test overdraft, should be permitted, fee should be applied
        System.out.println("Withdrawal of 100 should succeed: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 1)));
        // excessive overdraft should fail, no fee
        System.out.println("Withdrawal of 1000 should fail: "
                + a.withdrawal(1000, LocalDate.of(2016, Month.JANUARY, 1)));
        System.out.println("Final balance should be -135: " + a.getBalance());
        
        System.out.println("---------------------------------------------------");
        // test many withdrawals per month fee, first modify the strategy and adjust the balance
        a.setApplyFeesStrategy(new FrequentWithdrawalFeeStrategy());
        a.deposit(1135, LocalDate.of(2016, Month.JANUARY, 1));
        System.out.println("Starting balance " + a.getBalance());
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 1)));
        System.out.println("Balance should be 900: " + a.getBalance());
        // second-fifth withdrawals this month should succeed
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 3)));
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 5)));
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 15)));
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 22)));
        System.out.println("Sixth withdrawal of 100 incurs a fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.JANUARY, 23)));
        System.out.println("Much later withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.MARCH, 27)));
        System.out.println("Balance should be 265: " + a.getBalance());
        
        System.out.println("---------------------------------------------------");
        // test one withdrawal per month, first modify the strategy and adjust the balance
        a.setGrantWithdrawalStrategy(new OneWithdrawalPerMonthStrategy());
        a.deposit(735, LocalDate.of(2016, Month.APRIL, 1));
        System.out.println("Starting balance " + a.getBalance());
        System.out.println("Withdrawal of 100 should succeed, no fee: "
                + a.withdrawal(100, LocalDate.of(2016, Month.APRIL, 2)));
        System.out.println("Second withdrawal in month fails: "
                + a.withdrawal(100, LocalDate.of(2016, Month.APRIL, 29)));
        System.out.println("Third withdrawal in later month succeeds: "
                + a.withdrawal(100, LocalDate.of(2016, Month.MAY, 30)));

        System.out.println("Balance should be 800: " + a.getBalance());
        
        
    }

}
