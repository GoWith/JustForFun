package strategy;

@StrategyAnnotation("self_coupon")
public class SelfCouponStrategy extends AwardStrategy {
    @Override
    void exe() {
        System.out.println("self_coupon");
    }
}
