package MyWork;

import java.time.ZonedDateTime;
import java.util.Iterator;

public abstract class CapitalStrategy {
    public abstract double capital(Loan loan);

    public double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    public double unusedRiskFactorFor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }
    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }

    public double yearsTo(ZonedDateTime endDate, Loan loan) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime beginDate = (loan.getStart() == null ? today : loan.getStart());
        return endDate.getYear() - beginDate.getYear();
    }
}
