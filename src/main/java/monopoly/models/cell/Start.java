package monopoly.models.cell;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Start extends Cell{

    @JsonProperty("bonus")
    private int bonusPay;

    public Start(){

    }

    public int getBonusPay() {
        return bonusPay;
    }

    public void setBonusPay(int bonusPay) {
        this.bonusPay = bonusPay;
    }
}
