package monopoly.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import monopoly.models.cell.Cell;

import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bank {
    private String icon;
    private int initialMoney;
    private Map<Denomination, Integer> currency;

/*
    public Map<Cell, Player> getAssetsOwner() {
        return assetsOwner;
    }

    public void setAssetsOwner(Map<Cell, Player> assetsOwner) {
        this.assetsOwner = assetsOwner;
    }

    private Map<Cell, Player> assetsOwner;
*/

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
