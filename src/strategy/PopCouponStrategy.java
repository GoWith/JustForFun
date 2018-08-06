package strategy;

@StrategyAnnotation("pop_coupon")
public class PopCouponStrategy extends AwardStrategy {
    @Override
    void exe() {
        System.out.println("pop_coupon");
    }
}
