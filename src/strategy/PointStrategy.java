package strategy;

@StrategyAnnotation("point")
public class PointStrategy extends AwardStrategy {
    @Override
    void exe() {
        System.out.println("point");
    }
}
