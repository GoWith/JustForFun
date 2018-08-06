package command;

public class QueryCommand implements Command{
    Receiver receiver;
    public QueryCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        receiver.action();
    }
}
