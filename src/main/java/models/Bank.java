package models;

public class Bank {
    private String icon;
    private int initialMoney;
    private Currency currency;

    public Bank(){

    }

    public Bank(String icon, int initialMoney, Currency currency){
        this.icon = icon;
        this.initialMoney = initialMoney;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getInitialMoney() {
        return initialMoney;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setInitialMoney(Integer initialMoney) {
        this.initialMoney = initialMoney;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
