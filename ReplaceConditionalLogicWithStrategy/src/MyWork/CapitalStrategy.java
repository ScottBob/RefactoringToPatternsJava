package MyWork;

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
}
