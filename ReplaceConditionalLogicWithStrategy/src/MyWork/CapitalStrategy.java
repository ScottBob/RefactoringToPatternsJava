package MyWork;

import java.time.ZonedDateTime;
import java.util.Iterator;

public class CapitalStrategy {
    public double capital(Loan loan) {
        if (loan.getExpiry() == null && loan.getMaturity() != null)
            return loan.getCommitment() * loan.duration() * riskFactorFor(loan);
        if (loan.getExpiry() != null && loan.getMaturity() == null) {
            if (loan.getUnusedPercentage() != 1.0) {
                return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactorFor(loan);
            } else {
                return (loan.outstandingRiskAmount() * loan.duration() * riskFactorFor(loan))
                        + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactorFor(loan));
            }
        }
        return 0.0;
    }

    private double riskFactorFor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    private double unusedRiskFactorFor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }
    public double duration(Loan loan) {
        if (loan.getExpiry() == null && loan.getMaturity() != null)
            return weightedAverageDuration(loan);
        else if (loan.getExpiry() != null && loan.getMaturity() == null)
            return yearsTo(loan.getExpiry(), loan);
        return 0.0;
    }

    private double weightedAverageDuration(Loan loan) {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator loanPayments = loan.getPayments().iterator();
        while (loanPayments.hasNext()) {
            Payment payment = (Payment)loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date(), loan) * payment.amount();
        }
        if (loan.getCommitment() != 0.0)
            duration = weightedAverage / sumOfPayments;
        return duration;
    }

    private double yearsTo(ZonedDateTime endDate, Loan loan) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime beginDate = (loan.getStart() == null ? today : loan.getStart());
        return endDate.getYear() - beginDate.getYear();
    }
}
