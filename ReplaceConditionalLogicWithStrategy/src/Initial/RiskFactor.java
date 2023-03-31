package Initial;

public class RiskFactor {
    public static RiskFactor getFactors() {
        return new RiskFactor();
    }

    public double forRating(double riskRating) {
        return 0.035;
    }
}