package Initial;

import java.time.ZonedDateTime;

public class Payment {
    private final double amount;
    private final ZonedDateTime date;

    public Payment() {
        this.amount = 0.0;
        this.date = ZonedDateTime.now();
    }
    public Payment(double amount, ZonedDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public double amount() {
        return amount;
    }

    public ZonedDateTime date() {
        return date;
    }
}