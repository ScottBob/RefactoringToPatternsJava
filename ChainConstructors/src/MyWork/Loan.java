package MyWork;

import java.time.ZonedDateTime;

public class Loan {
    private final CapitalStrategy strategy;
    private float notional;
    private float outstanding;
    private int rating;
    private ZonedDateTime expiry;
    private ZonedDateTime maturity;

    public Loan(float notional, float outstanding, int rating, ZonedDateTime expiry) {
        this.strategy = new TermROC();
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
    }

    public Loan(float notional, float outstanding, int rating, ZonedDateTime expiry, ZonedDateTime maturity) {
        this.strategy = new RevolvingTermROC();
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }

    public Loan(CapitalStrategy strategy, float notional, float outstanding,
                int rating, ZonedDateTime expiry, ZonedDateTime maturity) {
        this.strategy = strategy;
        this.notional = notional;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }

    public CapitalStrategy GetCapitalStrategy() {
        return strategy;
    }
}
