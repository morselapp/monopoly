package models.cell;

import models.Player;
import models.Type;

public abstract class BuyableCell extends Cell implements IBuyable {

    private double price;
    private double rent;
    private double mortgage;

    public BuyableCell(Type type){
        super(type);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getMortgage() {
        return mortgage;
    }

    public void setMortgage(double mortgage) {
        this.mortgage = mortgage;
    }

    public void buy(Player player) {

    }
}
