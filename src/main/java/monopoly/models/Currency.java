package monopoly.models;

import java.util.Map;

/*
    Unclaimed - Not being used
 */
public class Currency {
    private Map<Denomination, Integer> currency;

    public Currency(){

    }

    public Currency(Map<Denomination, Integer> currency){
        this.currency = currency;
    }

    public Map<Denomination, Integer> getCurrency(){
        return this.currency;
    }

    public void setCurrency(Map<Denomination, Integer> currency){
        this.currency = currency;
    }
}
