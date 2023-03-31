package MyWork;

import java.time.ZonedDateTime;

public class CapitalStrategyRevolver extends CapitalStrategy {
    public double capital(Loan loan) {
        return (loan.outstandingRiskAmount() * loan.duration() * riskFactorFor(loan))
                + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactorFor(loan));
    }
}
