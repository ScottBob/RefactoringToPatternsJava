package MyWork;

import java.time.ZonedDateTime;

public class CapitalStrategyRevolver extends CapitalStrategy {
    public double capital(Loan loan) {
        return (loan.outstandingRiskAmount() * loan.duration() * riskFactorFor(loan))
                + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactorFor(loan));
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }

    private double yearsTo(ZonedDateTime endDate, Loan loan) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime beginDate = (loan.getStart() == null ? today : loan.getStart());
        return endDate.getYear() - beginDate.getYear();
    }

    private double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    private double unusedRiskFactorFor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }
}
