package MyWork;

import java.time.ZonedDateTime;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactorFor(loan);
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }

    private double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    private double yearsTo(ZonedDateTime endDate, Loan loan) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime beginDate = (loan.getStart() == null ? today : loan.getStart());
        return endDate.getYear() - beginDate.getYear();
    }
}
