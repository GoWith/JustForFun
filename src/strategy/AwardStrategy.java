package strategy;

public abstract class AwardStrategy {

    private boolean check(){
        return System.currentTimeMillis() % 2 == 0;

    }

    abstract void exe();

    public void award(){
        if(check()){
            exe();
        }
    }
}
