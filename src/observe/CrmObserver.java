package observe;

public class CrmObserver implements Observer{
    @Override
    public void OnMessage() {
        System.out.println("crm handle the message");
    }
}
