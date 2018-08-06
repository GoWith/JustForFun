package observe;

public class PromotionObserver implements Observer{
    @Override
    public void OnMessage() {
        System.out.println("promotion handle the message");
    }
}
