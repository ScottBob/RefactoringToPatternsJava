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
        this(new TermROC(), notional, outstanding, rating, expiry, null);
    }

    public Loan(float notional, float outstanding, int rating, ZonedDateTime expiry, ZonedDateTime maturity) {
        this(new RevolvingTermROC(), notional, outstanding, rating, expiry, maturity);
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
