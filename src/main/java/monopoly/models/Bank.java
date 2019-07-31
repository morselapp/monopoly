package monopoly.models;

import java.util.Map;

public class Bank {
    private String icon;
    private int initialMoney;
    private Map<Denomination, Integer> currency;

    public Bank(){

    }

    public Bank(String icon, int initialMoney, Map<Denomination, Integer> currency){
        this.icon = icon;
        this.initialMoney = initialMoney;
        this.currency = currency;
    }

    public Map<Denomination, Integer> getCurrency() {
        return currency;
    }

    public Integer getInitialMoney() {
        return initialMoney;
    }

    public void setCurrency(Map<Denomination, Integer> currency) {
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
