package models;

public class Player {
    private int id;
    private String name;
    private int initialMoney;
    private Currency currency;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
         this.name = name;
    }

    public int getInitialMoney(){
        return this.initialMoney;
    }

    public void setInitialMoney(int initialMoney){
        this.initialMoney = initialMoney;
    }

    public Currency getCurrency(){
        return this.currency;
    }

    public void setCurrency(Currency currency){
        this.currency = currency;
    }
}
