package Initial;

import java.time.ZonedDateTime;

public class Loan {
    private double commitment;
    private final double outstanding;
    private final int riskRating;
    private final ZonedDateTime maturity;
    private final ZonedDateTime expiry;
    private final CapitalStrategy capitalStrategy;

    public Loan(double commitment, int riskRating, ZonedDateTime maturity) {
        this(commitment, 0.00, riskRating, maturity, null);
    }

    public Loan(double commitment, int riskRating, ZonedDateTime maturity, ZonedDateTime expiry) {
        this(commitment, 0.00, riskRating, maturity, expiry);
    }

    public Loan(double commitment, double outstanding, int riskRating, ZonedDateTime maturity, ZonedDateTime expiry) {
        this(null, commitment, outstanding, riskRating, maturity, expiry);
    }

    public Loan(CapitalStrategy capitalStrategy, double commitment, int riskRating, ZonedDateTime maturity, ZonedDateTime expiry) {
        this(capitalStrategy, commitment, 0.00, riskRating, maturity, expiry);
    }

    public Loan(CapitalStrategy capitalStrategy, double commitment,
                double outstanding, int riskRating,
                ZonedDateTime maturity, ZonedDateTime expiry) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.expiry = expiry;

        if (capitalStrategy != null) {
            this.capitalStrategy = capitalStrategy;
        } else {
            if (expiry == null)
                this.capitalStrategy = new CapitalStrategyTermLoan();
            else if (maturity == null)
                this.capitalStrategy = new CapitalStrategyRevolver();
            else
                this.capitalStrategy = new CapitalStrategyRCTL();
        }
    }

    public CapitalStrategy GetCapitalStrategy() {
        return capitalStrategy;
    }
}
