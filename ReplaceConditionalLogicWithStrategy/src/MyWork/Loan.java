package MyWork;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Loan {
    private static final long MILLIS_PER_DAY = 86400000;
    private static final long DAYS_PER_YEAR = 365;
    private double commitment = 1.0;
    private final double outstanding = 0;
    private final int riskRating;
    private final ZonedDateTime start;
    private final ZonedDateTime maturity;
    private final ZonedDateTime expiry;
    private double unusedPercentage;
    ArrayList<Payment> payments = new ArrayList<>();

    public Loan(double commitment, double notSureWhatThisIs, ZonedDateTime start, ZonedDateTime expiry, ZonedDateTime maturity, int riskRating) {
        this.expiry = expiry;
        this.commitment = commitment;
        this.start = start;
        this.maturity = maturity;
        this.riskRating = riskRating;
        this.unusedPercentage = 1.0;
    }

    public static Loan newTermLoan(double commitment, ZonedDateTime start, ZonedDateTime maturity, int riskRating)
    {
        return new Loan(commitment, commitment, start, null,
                maturity, riskRating);
    }

    public static Loan newRevolver(double commitment, ZonedDateTime start, ZonedDateTime expiry, int riskRating)
    {
        return new Loan(commitment, 0, start, expiry,
                null, riskRating);
    }

    public static Loan newAdvisedLine(double commitment, ZonedDateTime start, ZonedDateTime expiry, int riskRating)
    {
        if (riskRating > 3) return null;
        Loan advisedLine = new Loan(commitment, 0, start, expiry,
                null, riskRating);
        advisedLine.setUnusedPercentage(0.1);
        return advisedLine;
    }

    public void payment(double amount, ZonedDateTime paymentDate)
    {
        payments.add(new Payment(amount, paymentDate));
    }

    public double capital() {
        if (expiry == null && maturity !=null) {
            return commitment * duration() * riskFactor();
        }
        if (expiry != null && maturity == null) {
            if (getUnusedPercentage() != 1.0) {
                return commitment * getUnusedPercentage() * duration() * riskFactor();
            } else {
                return (outstandingRiskAmount() * duration() * riskFactor())
                        + (unusedRiskAmount() * duration() * unusedRiskFactor());
            }
        }
        return 0.0;
    }

    private double outstandingRiskAmount() {
        return outstanding;
    }

    private double getUnusedPercentage() {
        return unusedPercentage;
    }

    private void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    public double duration() {
        if (expiry == null && maturity != null)
            return weightedAverageDuration();
        else if (expiry != null && maturity == null)
            return yearsTo(expiry);
        return 0.0;
    }

    private double weightedAverageDuration() {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator loanPayments = payments.iterator();
        while (loanPayments.hasNext()) {
            Payment payment = (Payment)loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date()) * payment.amount();
        }
        if (commitment != 0.0)
            duration = weightedAverage / sumOfPayments;
        return duration;
    }

    private double yearsTo(ZonedDateTime endDate) {
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime beginDate = (start == null ? today : start);
        return endDate.getYear() - beginDate.getYear();
    }

    private double riskFactor() {
        return RiskFactor.getFactors().forRating(riskRating);
    }

    private double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(riskRating);
    }

    private double unusedRiskAmount() {
        return (commitment - outstanding);
    }
}
