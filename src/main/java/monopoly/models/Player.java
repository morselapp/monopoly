package monopoly.models;

import java.util.Map;

public class Player {
    private int id;
    private String name;
    private String icon;
    private int initialMoney;
    private Map<Denomination, Integer> currency;

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

    public Map<Denomination, Integer> getCurrency(){
        return this.currency;
    }

    public void setCurrency(Map<Denomination, Integer> currency){
        this.currency = currency;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
