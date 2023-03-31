package MyWork;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class CapitalCalculationTests {
    private static final double LOAN_AMOUNT = 3000.0;
    private static final int HIGH_RISK_RATING = 1;
    private static final double TWO_DIGIT_PRECISION = 0.01;
    private final ZonedDateTime maturity = ZonedDateTime.parse("2020-10-02T00:00:00.000Z");
    private final ZonedDateTime expiry = ZonedDateTime.parse("2021-10-02T00:00:00.000Z");

    private final double Commitment = 3.0;
    private final double Outstanding = 12.9;
    private RiskAdjustedCapitalStrategy riskAdjustedCapitalStrategy = new RiskAdjustedCapitalStrategy();
    private final int RiskRating = 5;

    @Test
    public void test_term_loan_same_payments() {
        ZonedDateTime start = november(20, 2003);
        ZonedDateTime maturity = november(20, 2006);
        Loan termLoan = Loan.newTermLoan(LOAN_AMOUNT, start, maturity, HIGH_RISK_RATING);
        termLoan.payment(1000.00, november(20, 2004));
        termLoan.payment(1000.00, november(20, 2005));
        termLoan.payment(1000.00, november(20, 2006));
        Assert.assertEquals("duration", 2.0, termLoan.duration(), TWO_DIGIT_PRECISION);
        Assert.assertEquals("capital", 210.0, termLoan.capital(), TWO_DIGIT_PRECISION);
    }

    @Test
    public void test_revolving_loan_same_payments() {
        ZonedDateTime start = november(20, 2003);
        ZonedDateTime maturity = november(20, 2006);
        Loan termLoan = Loan.newRevolver(LOAN_AMOUNT, start, maturity, HIGH_RISK_RATING);
        termLoan.payment(1000.00, november(20, 2004));
        termLoan.payment(1000.00, november(20, 2005));
        termLoan.payment(1000.00, november(20, 2006));
        Assert.assertEquals("duration", 3.0, termLoan.duration(), TWO_DIGIT_PRECISION);
        Assert.assertEquals("capital", 90.0, termLoan.capital(), TWO_DIGIT_PRECISION);
    }

    @Test
    public void test_advised_loan_same_payments() {
        ZonedDateTime start = november(20, 2003);
        ZonedDateTime maturity = november(20, 2006);
        Loan termLoan = Loan.newAdvisedLine(LOAN_AMOUNT, start, maturity, HIGH_RISK_RATING);
        termLoan.payment(1000.00, november(20, 2004));
        termLoan.payment(1000.00, november(20, 2005));
        termLoan.payment(1000.00, november(20, 2006));
        Assert.assertEquals("duration", 3.0, termLoan.duration(), TWO_DIGIT_PRECISION);
        Assert.assertEquals("capital", 31.5, termLoan.capital(), TWO_DIGIT_PRECISION);
    }

    /*
    @Test
    public void test_term_loan_no_payments() {
        Loan loan = new Loan(Commitment, RiskRating, maturity);
        Assert.assertThat(loan.GetCapitalStrategy(), new IsInstanceOf(CapitalStrategyTermLoan.class));
    }

    @Test
    public void test_term_loan_one_payment() {
        Loan loan = new Loan(Commitment, RiskRating, maturity);
        Assert.assertThat(loan.GetCapitalStrategy(), new IsInstanceOf(CapitalStrategyTermLoan.class));
    }

    @Test
    public void test_revolver_loan_no_payments() {
        Loan loan = new Loan(Commitment, RiskRating, null, expiry);
        Assert.assertThat(loan.GetCapitalStrategy(), new IsInstanceOf(CapitalStrategyRevolver.class));
    }

    @Test
    public void test_RCTL_loan_one_payment() {
        Loan loan = new Loan(Commitment, Outstanding, RiskRating, maturity, expiry);
        Assert.assertThat(loan.GetCapitalStrategy(), new IsInstanceOf(CapitalStrategyRCTL.class));
    }

    @Test
    public void test_term_loan_with_risk_adjusted_capital_strategy() {
        Loan loan = new Loan(riskAdjustedCapitalStrategy,
                Commitment, Outstanding, RiskRating,
                maturity, null);

            Assert.assertThat(loan.GetCapitalStrategy(), new IsInstanceOf(RiskAdjustedCapitalStrategy.class));
    }

     */

    private ZonedDateTime november(int day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 11, day);
        String dateString =  String.format("%4d-11-%2dT00:00:00.000Z", year, day);
        return ZonedDateTime.parse(dateString);
        //return ZonedDateTime.of(year, 11, day, 0, 0, 0, 0, ZoneId.of("CST"));
    }

}
