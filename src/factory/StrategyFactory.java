package factory;

import strategy.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class StrategyFactory {
    private static List<Class<? extends AwardStrategy>> strategyList = new ArrayList<>();

    static {
        strategyList.add(PopCouponStrategy.class);
        strategyList.add(SelfCouponStrategy.class);
        strategyList.add(BeanStrategy.class);
        strategyList.add(PointStrategy.class);
    }

    public static AwardStrategy createdStrategy(String type) {
        try {
            for (Class<? extends AwardStrategy> awardStrategy : strategyList) {
                StrategyAnnotation annotation = getAnnotation(awardStrategy);
                if (annotation.value().equalsIgnoreCase(type)) {
                    return awardStrategy.newInstance();
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("there is no right strategy");
    }

    private static StrategyAnnotation getAnnotation(Class<? extends AwardStrategy> awardStrategy) {
        Annotation[] annotations = awardStrategy.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof StrategyAnnotation) {
                return (StrategyAnnotation) annotation;
            }
        }
        return null;
    }
}
