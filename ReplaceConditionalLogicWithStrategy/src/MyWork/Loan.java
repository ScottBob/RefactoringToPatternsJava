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
        return new CapitalStrategy().capital(this);
    }

    public double outstandingRiskAmount() {
        return outstanding;
    }

    public double getUnusedPercentage() {
        return unusedPercentage;
    }

    private void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    public double duration() {
        return new CapitalStrategy().duration(this);
    }

    public double unusedRiskAmount() {
        return (commitment - outstanding);
    }

    public ZonedDateTime getExpiry() {
        return expiry;
    }

    public ZonedDateTime getMaturity() {
        return maturity;
    }

    public double getCommitment() {
        return commitment;
    }

    public double getRiskRating() {
        return riskRating;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public ZonedDateTime getStart() {
        return start;
    }
}
