public class Money {
    private double money;

    public Money (){
        money = 1000.0;
    }

    public Money(double money){
        this.money = money;
    }

    public void add (){
        money += 100;
    }

    public void add(double addMoney){
        money += addMoney;
    }

    public void subtract(double subMoney){
        money -= subMoney;
    }

    public void subtract (){
        money -= 100;
    }

    public double getMoney(){
        return money;
    }
}
