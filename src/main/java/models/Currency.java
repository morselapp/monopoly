package models;

import java.util.Map;

public class Currency {
    private Map<Denomination, Integer> currency;

    public Map<Denomination, Integer> getCurrency(){
        return this.currency;
    }

    public void setCurrency(Map<Denomination, Integer> currency){
        this.currency = currency;
    }
}
