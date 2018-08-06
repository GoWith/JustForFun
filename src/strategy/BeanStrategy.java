package strategy;

@StrategyAnnotation("bean")
public class BeanStrategy extends AwardStrategy {
    @Override
    void exe() {
        System.out.println("bean");
    }
}
